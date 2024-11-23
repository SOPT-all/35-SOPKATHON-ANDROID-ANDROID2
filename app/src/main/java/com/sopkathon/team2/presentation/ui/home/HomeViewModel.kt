import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopkathon.team2.data.model.response.ResponseUserDto
import com.sopkathon.team2.data.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val service = RetrofitInstance.service

    private val _userInfo = MutableStateFlow<ResponseUserDto?>(null)
    val userInfo: StateFlow<ResponseUserDto?> = _userInfo


    init {
        getUserInfo(1)
    }

    private fun getUserInfo(userId: Long) {
        viewModelScope.launch {
            try {
                val response = service.getUserById(userId)

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _userInfo.value = responseBody.data
                        Log.d(
                            "ㅋㅋ",
                            "Success: ${response.body()!!.status}, Data: ${responseBody.data}"
                        )

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