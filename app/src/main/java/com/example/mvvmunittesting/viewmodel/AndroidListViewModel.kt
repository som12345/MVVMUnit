package com.example.mvvmunittesting.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmunittesting.model.ApiResponseModel
import com.example.mvvmunittesting.repo.RetrofitApiRepo
import com.example.mvvmunittesting.util.Resource
import kotlinx.coroutines.launch

class AndroidListViewModel @ViewModelInject constructor(
    var mainRepository: RetrofitApiRepo
) : ViewModel() {

    var _users = MutableLiveData<Resource<ApiResponseModel>>()
    val users: LiveData<Resource<ApiResponseModel>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
                mainRepository.getAndroidList().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
        }
    }
}