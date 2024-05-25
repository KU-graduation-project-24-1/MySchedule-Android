package com.uuranus.storelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.domain.GetStoreListUseCase
import com.uuranus.model.Store
import com.uuranus.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoreListViewModel @Inject constructor(
    private val getStoreListUseCase: GetStoreListUseCase
) : ViewModel() {
    private val _stores = MutableStateFlow<List<Store>>(emptyList())

    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> get() = _userData
    val stores: StateFlow<List<Store>> get() = _stores

    fun fetchStores(authorization: String) {
        viewModelScope.launch {
            try {
                val storeList = getStoreListUseCase(authorization)
                _stores.value = storeList
            } catch (e: Exception) {
                Log.e("StoreListViewError", e.toString())
            }
        }
    }
}