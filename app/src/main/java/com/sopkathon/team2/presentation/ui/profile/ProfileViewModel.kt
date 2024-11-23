package com.sopkathon.team2.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopkathon.team2.data.model.response.ResponseProfileDto
import com.sopkathon.team2.data.service.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response


class ProfileViewModel : ViewModel() {
    private val service = RetrofitInstance.service

    private val _response = MutableStateFlow<Response<ResponseProfileDto>?>(null)
    val response: StateFlow<Response<ResponseProfileDto>?> get() = _response

    fun loadProfiles(userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = service.loadProfiles(userId)
                _response.value = result
                Log.d("Profile", result.body().toString())
            } catch (e: Exception) {
                e.printStackTrace()
                _response.value = null
                Log.d("Profile", e.toString())
            }
        }
    }
}
