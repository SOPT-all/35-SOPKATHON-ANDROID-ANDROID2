import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopkathon.team2.data.model.response.ResponseUserDto
import com.sopkathon.team2.data.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val service = RetrofitInstance.service


    private val _userInfo = MutableStateFlow<Response<ResponseUserDto>?>(null)
    val userInfo: StateFlow<Response<ResponseUserDto>?> = _userInfo


    init {
        getUserInfo(1)
    }

    private fun getUserInfo(userId: Long) {
        viewModelScope.launch {
            try {
                val response = service.getUserById(userId)
                _userInfo.value = response
            } catch (e: Exception) {
            }
        }
    }
}