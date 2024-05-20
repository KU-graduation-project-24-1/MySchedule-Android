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
//                isLoggedIn = preferences.isLoggedIn
//            )
            UserData(
                storeId = 1,
                memberId = 1,
                name = "김알바",
                workerType = "사장",
                isLoggedIn = false
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
                }
            }
        } catch (ioException: IOException) {
            Log.e("UserPreference", "Failed to update user preferences", ioException)
        }
    }

}