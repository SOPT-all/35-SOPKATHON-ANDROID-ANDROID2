package com.sopkathon.team2.presentation.ui.write

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sopkathon.team2.data.datasource.local.ImageLocalDataSource

class WriteViewModel(
//    private val imageLocalDataSource: ImageLocalDataSource
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

//    fun saveImage() {
//        imageLocalDataSource.imageUri = imageUri
//    }

    fun getTextSize() = text.length.toString()

}
