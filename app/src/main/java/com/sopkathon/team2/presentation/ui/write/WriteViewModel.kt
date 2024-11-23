package com.sopkathon.team2.presentation.ui.write

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.data.datasource.local.ImageLocalDataSource
import com.sopkathon.team2.data.service.RetrofitInstance
import com.sopkathon.team2.data.service.Service
import kotlinx.coroutines.launch

class WriteViewModel(
//    private val imageLocalDataSource: ImageLocalDataSource
    private val service: Service = RetrofitInstance.service

) : ViewModel() {

    var text by mutableStateOf("")
        private set

    var imageUri by mutableStateOf<Uri?>(null)
        private set

    fun onChangedTextField(newText: String) {
        text = newText
    }

    fun onChangedImage(image: Uri?) {
        imageUri = image
    }

    fun postContent() {
        viewModelScope.launch {
            service.postContent(userId = 1, content = text)
        }
    }

//    fun saveImage() {
//        imageLocalDataSource.imageUri = imageUri
//    }

    fun getTextSize() = text.length.toString()

}
