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
            UserData(
                storeId = 2,
                memberId = 1,
                name = "김알바",
                workerType = "사장",
                isLoggedIn = true,
                storeName = "ㅁㅁ 떡볶이 건대입구점",
                accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3IiwiaWF0IjoxNzE2MjE3NDE5LCJleHAiOjE3NDc3NTM0MTl9.3Etu1SIueL83j5SQ0ClXPM2gF97HP1Q-l-SwFL5nCBU",
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