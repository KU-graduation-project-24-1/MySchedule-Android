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
//                memberId = 2,
//                name = "최사장",
//                workerType = "사장",
//                isLoggedIn = true,
//                storeName = "KU카페 건대입구점",
//                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzE2NDI5MjMyLCJleHAiOjE3NDc5NjUyMzJ9.F1D9DjSB7GMGEJryiBt64Kl1xrxX9_t3eM14QZvg1Gg",
//                refreshToken = ""
//            )

            UserData(
                storeId = 1,
                memberId = 5,
                name = "박직원",
                workerType = "직원",
                isLoggedIn = true,
                storeName = "KU카페 건대입구점",
                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNzE2NjEzOTIyLCJleHAiOjE3NDgxNDk5MjJ9." +
                        "J-bK-Pjgo--3ypuqqEwAkADvVoCCyi3qU6LABHyTIyY",
                refreshToken = ""
            )
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