package com.sopkathon.team2.presentation.ui.complete

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopkathon.team2.data.datasource.local.ImageLocalDataSource
import com.sopkathon.team2.data.model.response.ResponsePotatoDto
import com.sopkathon.team2.data.model.response.ResponseWrapper
import com.sopkathon.team2.data.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompleteViewModel(
    private val imageLocalDataSource: ImageLocalDataSource
) : ViewModel() {
    private val service = RetrofitInstance.service


    private val _potato = MutableStateFlow<ResponseWrapper<ResponsePotatoDto>?>(null)
    val potato: StateFlow<ResponseWrapper<ResponsePotatoDto>?> = _potato

    private val _uri = MutableStateFlow<Uri?>(null)
    val uri: StateFlow<Uri?> = _uri

    init {
        imageLocalDataSource.boardId?.let { getPotatoInfo(it.toLong()) }
    }
    private fun getPotatoInfo(boardId: Long) {
        viewModelScope.launch {
            _uri.value = imageLocalDataSource.imageUri
            try {
                val response = service.getPotatoById(boardId)
                _potato.value = response.body()
                Log.d("##", response.body().toString())
            } catch (e: Exception) {
                Log.d("##", e.toString())
            }
        }
    }

}