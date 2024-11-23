import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InstagramShareViewModel : ViewModel() {

    private val _uri = MutableStateFlow("")
    val uri: StateFlow<String> = _uri


    fun setUri(uri: String) {
        _uri.value = uri
        Log.d("ㅋㅋ", uri)
    }

}