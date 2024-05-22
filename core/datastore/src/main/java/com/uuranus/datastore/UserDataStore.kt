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
//                storeName = "ㅁㅁ 떡볶이 건대입구점",
//                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzE2MzQ3NDM3LCJleHAiOjE3NDc4ODM0Mzd9.LNJp9aVkXEoQn_ZKKxqU2PtH9PRrk6_E_m5FPI7G-lI",
//                refreshToken = ""
//            )

            UserData(
                storeId = 1,
                memberId = 3,
                name = "박직원",
                workerType = "직원",
                isLoggedIn = true,
                storeName = "ㅁㅁ 떡볶이 건대입구점",
                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzE2MzUwNzE3LCJleHAiOjE3NDc4ODY3MTd9.6gEs28OXrVPCzQid8t5hlm83I2GYMTn3NRxImNE1Cm8",
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