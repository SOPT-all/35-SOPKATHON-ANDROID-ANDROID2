import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModel
import com.sopkathon.team2.presentation.ui.home.component.InstagramCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class InstagramShareViewModel : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun captureInstagramCardBitmap(context: Context): Bitmap {
        Log.d("InstagramShare", "Entered captureInstagramCardBitmap")
        return withContext(Dispatchers.Main) {
            suspendCancellableCoroutine { continuation ->
                val composeView = ComposeView(context).apply {
                    setContent {
                        InstagramCard()
                    }
                }
                Log.d("InstagramShare", "ComposeView created")

                val frameLayout = FrameLayout(context).apply {
                    addView(composeView)
                }
                Log.d("InstagramShare", "FrameLayout created and ComposeView added")

                frameLayout.viewTreeObserver.addOnGlobalLayoutListener {
                    Log.d("InstagramShare", "onGlobalLayoutListener triggered")
                    try {
                        val bitmap = composeView.drawToBitmap()
                        Log.d("InstagramShare", "Bitmap captured successfully via GlobalLayout")
                        continuation.resume(bitmap) {}
                    } catch (e: Exception) {
                        Log.d(
                            "InstagramShare",
                            "Error capturing bitmap via GlobalLayout: ${e.message}"
                        )
                        continuation.resumeWith(Result.failure(e))
                    }
                }
                Log.d("InstagramShare", "onGlobalLayoutListener attached")

                frameLayout.post {
                    Log.d("InstagramShare", "Post triggered for manual layout")
                    try {
                        frameLayout.measure(
                            FrameLayout.LayoutParams.WRAP_CONTENT,
                            FrameLayout.LayoutParams.WRAP_CONTENT
                        )
                        frameLayout.layout(
                            0,
                            0,
                            frameLayout.measuredWidth,
                            frameLayout.measuredHeight
                        )
                        Log.d("InstagramShare", "FrameLayout manually measured and laid out")

                        val bitmap = composeView.drawToBitmap()
                        Log.d("InstagramShare", "Bitmap captured successfully after manual layout")
                        continuation.resume(bitmap) {}
                    } catch (e: Exception) {
                        Log.d(
                            "InstagramShare",
                            "Error capturing bitmap after manual layout: ${e.message}"
                        )
                        continuation.resumeWith(Result.failure(e))
                    }
                }
            }
        }
    }

    suspend fun shareToInstagramStory(context: Context, bitmap: Bitmap) {
        Log.d("InstagramShare", "Entered shareToInstagramStory")
        withContext(Dispatchers.IO) {
            val file =
                File(context.cacheDir, "instagram_story_image_${System.currentTimeMillis()}.png")
            try {
                FileOutputStream(file).use { fos ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                }
                Log.d("InstagramShare", "File saved at: ${file.absolutePath}")

                val uri: Uri = try {
                    FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        file
                    )
                } catch (e: IllegalArgumentException) {
                    Log.d("InstagramShare", "Failed to create URI: ${e.message}")
                    throw IllegalStateException(
                        "Failed to create file URI for Instagram sharing",
                        e
                    )
                }
                Log.d("InstagramShare", "URI generated: $uri")

                val intent = Intent("com.instagram.share.ADD_TO_STORY").apply {
                    setDataAndType(uri, "image/png")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                withContext(Dispatchers.Main) {
                    if (context.packageManager.resolveActivity(intent, 0) != null) {
                        context.startActivity(intent)
                        Log.d("InstagramShare", "Instagram story sharing started")
                    } else {
                        Log.d(
                            "InstagramShare",
                            "Instagram app is not installed or does not support this Intent"
                        )
                    }
                }
            } finally {
                if (file.exists()) {
                    file.delete()
                    Log.d("InstagramShare", "Temporary file deleted: ${file.absolutePath}")
                }
            }
        }
    }
}