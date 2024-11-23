package com.sopkathon.team2.presentation.ui.bottomsheet

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopkathon.team2.data.model.response.Users
import com.sopkathon.team2.data.service.RetrofitInstance
import kotlinx.coroutines.launch

class BottomSheetViewModel(

) : ViewModel() {
    private val service = RetrofitInstance.service

    var usersInfo by mutableStateOf(emptyList<Users>())
        private set

    fun getUsersInfo() {
        viewModelScope.launch {
            try {
                val response = service.getUsers()

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if (responseBody.status == 200) {
                            usersInfo = responseBody.data
                            Log.d("test", usersInfo.toString())
                            Log.d(
                                "ㅋㅋ",
                                "Success: ${response.body()!!.status}, Data: ${responseBody.data}"
                            )
                        }
                    } else {
                        Log.d("ㅋㅋ", "Error: Response body is null")
                    }
                } else {
                    Log.d("ㅋㅋ", "Error: ${response.code()} - ${response.message()}")

                }
            } catch (e: Exception) {
                Log.d("ㅋㅋ", "User Info Fetched: ${e.message}")
            }
        }
    }
}
