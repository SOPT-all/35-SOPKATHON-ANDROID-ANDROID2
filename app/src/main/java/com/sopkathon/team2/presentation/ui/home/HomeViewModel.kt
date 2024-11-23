import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopkathon.team2.data.model.response.ResponseDummyDto
import com.sopkathon.team2.data.service.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val service = RetrofitInstance.service

    private val _potatoes = MutableStateFlow<List<ResponseDummyDto>>(emptyList())
    val potatoes: StateFlow<List<ResponseDummyDto>> get() = _potatoes

    fun loadPotatoes(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.loadPotatoes(id)
                _potatoes.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}