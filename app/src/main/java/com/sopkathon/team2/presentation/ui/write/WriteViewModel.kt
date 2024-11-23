package com.sopkathon.team2.presentation.ui.write

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WriteViewModel : ViewModel() {

    var text by mutableStateOf("")
        private set

    fun onChangedTextField(newText: String) {
        text = newText
    }

    fun getTextSize() = text.length.toString()

    var image by mutableStateOf<Uri?>(null)
        private set

}
