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

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

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
                        _userInfo.value = responseBody.data // `data` 내부 값을 Flow에 전달
                        _error.value = null
                    } else {
                        _error.value = "Response body is null"
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}