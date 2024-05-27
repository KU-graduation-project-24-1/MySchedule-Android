package com.uuranus.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.uuranus.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val dataStore: DataStore<UserPreferences>,
) {

    val userData: Flow<UserData> = dataStore.data
        .map { preferences ->
//            UserData(
//                storeId = preferences.storeId,
//                memberId = preferences.memberId,
//                name = preferences.name,
//                workerType = preferences.workerType,
//                isLoggedIn = preferences.isLoggedIn,
//                storeName = preferences.storeName,
//                accessToken = preferences.accessToken,
//                refreshToken = preferences.refreshToken
//            )

//            UserData(
//                storeId = 1,
//                memberId = 4,
//                name = "황유란",
//                workerType = "아르바이트",
//                isLoggedIn = true,
//                storeName = "KU카페 건대입구점",
//                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE2ODAzNjczLCJleHAiOjE3NDgzMzk2NzN9.dLRgt99b84OEYmQ1bGio5ABj8ZOkK1HPNXdQIS5dmi0",
//                refreshToken = ""
//            )

            UserData(
                storeId = 1,
                memberId = 2,
                name = "정경은",
                workerType = "사장",
                isLoggedIn = true,
                storeName = "KU카페 건대입구점",
                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzE2ODA2NDg5LCJleHAiOjE3NDgzNDI0ODl9.K1d1mj6MFgTG3emAgrpbcbNL8iqG_p7XY3T8JcHzjl8",
                refreshToken = ""
            )

//            UserData(
//                storeId = 1,
//                memberId = 1,
//                name = "김동준",
//                workerType = "매니저",
//                isLoggedIn = true,
//                storeName = "KU카페 건대입구점",
//                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzE2ODAyNzE2LCJleHAiOjE3NDgzMzg3MTZ9.iXuQdSj1GD1el7qIWxuf2PIAldk-N8cmVgBq4g1lfBU",
//                refreshToken = ""
//            )


        }

    suspend fun setUserData(userData: UserData) {
        try {
            dataStore.updateData {
                it.copy {
                    storeId = userData.storeId
                    memberId = userData.memberId
                    name = userData.name
                    workerType = userData.workerType
                    isLoggedIn = userData.isLoggedIn
                    storeName = userData.storeName
                    accessToken = userData.accessToken
                    refreshToken = userData.refreshToken
                }
            }
        } catch (ioException: IOException) {
            Log.e("UserPreference", "Failed to update user preferences", ioException)
        }
    }

}