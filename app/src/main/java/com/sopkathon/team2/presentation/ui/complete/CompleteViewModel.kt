package com.sopkathon.team2.presentation.ui.complete

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopkathon.team2.data.model.response.ResponsePotatoDto
import com.sopkathon.team2.data.model.response.ResponseWrapper
import com.sopkathon.team2.data.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompleteViewModel : ViewModel() {
    private val service = RetrofitInstance.service


    private val _potato = MutableStateFlow<ResponseWrapper<ResponsePotatoDto>?>(null)
    val potato: StateFlow<ResponseWrapper<ResponsePotatoDto>?> = _potato

    fun getPotatoInfo(userId: Long) {
        viewModelScope.launch {
            try {
                val response = service.getPotatoById(userId)
                _potato.value = response.body()
                Log.d("##", response.body().toString())
            } catch (e: Exception) {
                Log.d("##", e.toString())
            }
        }
    }
}