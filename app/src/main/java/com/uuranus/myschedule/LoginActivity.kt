package com.uuranus.myschedule

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.uuranus.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.uuranus.myschedule.main.MainActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import android.Manifest
import androidx.compose.runtime.CompositionLocalProvider
import com.uuranus.myschedule.ui.LoginMain
import com.uuranus.myschedule.ui.StoreListMain
import com.uuranus.navigation.AppComposeNavigator
import com.uuranus.navigation.LocalComposeNavigator
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    @Inject
    internal lateinit var composeNavigator: AppComposeNavigator

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel: LoginViewModel by viewModels()

        KakaoSdk.init(this, "a49003fec5167f6992c1f845710462b9")

        askNotificationPermission()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "FCM registration token: $token"
            Log.d("FCM", msg)
            //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

        var keyHash = Utility.getKeyHash(this)
        Log.d("hash",keyHash)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.userData.collectLatest {data ->
                    if(data.isLoggedIn)  //카톡 로그인함
                    {
                        if(data.name.isNotEmpty())  // 기존계정
                        {
                            setContent {
                                CompositionLocalProvider (LocalComposeNavigator provides composeNavigator
                                ){
                                    StoreListMain(composeNavigator = composeNavigator)
                                }
                            }
                        }
                        else if(data.storeId>111){  // 기존 계정이고 가게를 고른경우
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java,
                                ).apply {
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                }
                            )
                            finish()
                        }
                    } else {
                        setContent {
                            CompositionLocalProvider(LocalComposeNavigator provides composeNavigator
                            ) {
                                LoginMain(composeNavigator = composeNavigator,loginViewModel, onClickLogin= ::onClickLogin)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission already granted, handle accordingly
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // Explanation needed, display rationale to the user
            } else {
                // Request permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}

fun onClickLogin(context: Context) {
    // 로그인 조합 예제

// 카카오계정으로 로그인 공통 callback 구성
// 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("로그인", "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i("로그인", "카카오계정으로 로그인 성공 ${token.idToken}")
            // 서버에 idToken과 Fcm토큰 로그인 API - 요청에 성공하면 그때서야 viewmodel.updateLoginStatus(true)
        }
    }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e("로그인", "카카오톡으로 로그인 실패", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            } else if (token != null) {
                Log.i("로그인", "카카오톡으로 로그인 성공 ${token.idToken}")

            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }
}


