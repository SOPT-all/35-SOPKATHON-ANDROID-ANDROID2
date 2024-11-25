package com.sopkathon.team2.presentation.ui.write

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.data.datasource.local.ImageLocalDataSource
import com.sopkathon.team2.data.model.request.RequestContentDto
import com.sopkathon.team2.data.service.RetrofitInstance
import com.sopkathon.team2.data.service.Service
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WriteViewModel(
    private val imageLocalDataSource: ImageLocalDataSource,
    private val service: Service = RetrofitInstance.service

) : ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    var text by mutableStateOf("")
        private set

    var imageUri by mutableStateOf<Uri?>(null)
        private set

    fun onChangedTextField(newText: String) {
        text = newText
    }

    var boardId by mutableIntStateOf(0)
        private set

    fun onChangedImage(image: Uri?) {
        imageUri = image
    }



    fun postContent(onComplete: () -> Unit) {

        viewModelScope.launch {
            _isLoading.value = true
            val startTime = System.currentTimeMillis()
            try {
                val response = service.postContent(userId = 1, requestContentDto = RequestContentDto(text))

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        boardId = responseBody.data.boardId
                        imageLocalDataSource.boardId = boardId
                    } else {
                        Log.d("ㅋㅋ", "Error: Response body is null")
                    }
                } else {
                    Log.d("ㅋㅋ", "Error: ${response.code()} - ${response.message()}")

                }
            } catch (e: Exception) {
                Log.d("ㅋㅋ", "User Info Fetched: ${e.message}")
            } finally {
                val elapsedTime = System.currentTimeMillis() - startTime
                val remainingTime = 2000L - elapsedTime
                if (remainingTime > 0) {
                    delay(remainingTime)
                }
                _isLoading.value = false // 로딩 종료
                onComplete() // 완료 콜백 호출
            }
        }
    }

    fun saveImage() {
        imageLocalDataSource.imageUri = imageUri
    }

    fun getTextSize() = text.length.toString()

}
