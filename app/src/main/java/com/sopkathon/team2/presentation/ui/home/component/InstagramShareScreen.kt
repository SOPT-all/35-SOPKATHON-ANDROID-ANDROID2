package com.sopkathon.team2.presentation.ui.home.component

import InstagramShareViewModel
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.presentation.util.noRippleClickable
import com.sopkathon.team2.presentation.util.roundedBackgroundWithPadding
import com.sopkathon.team2.ui.theme.GAMJATheme
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch
import org.sopt.and.R
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
fun InstagramShareScreen(
    nickname: String,
    level: Int,
    modifier: Modifier = Modifier,
    viewModel: InstagramShareViewModel = viewModel(),
    closeButtonClicked: () -> Unit = {},
    shareClicked: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val captureController = rememberCaptureController()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 0.6f
                    renderEffect = BlurEffect(radiusX = 20f, radiusY = 20f)
                }
                .background(Color.Black.copy(alpha = 0.6f))
        ) {}

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 27.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_instagram_share_close),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable {
                        closeButtonClicked()
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            InstagramCard(
                nickname = nickname, level = level,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .capturable(captureController)
            )


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "인스타그램 공유하기",
                color = GAMJATheme.colors.black,
                style = GAMJATheme.typography.bodyMedium16,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedBackgroundWithPadding(
                        padding = PaddingValues(vertical = 15.dp),
                        cornerRadius = 10.dp,
                        backgroundColor = GAMJATheme.colors.main
                    )
                    .noRippleClickable {
                        scope.launch {
                            try {
                                val bitmap =
                                    captureController
                                        .captureAsync()
                                        .await()
                                        .asAndroidBitmap()

                                saveImageToGallery(
                                    context,
                                    bitmap,
                                    "instagram_story_image_${System.currentTimeMillis()}.jpg"
                                )

                                Log.d("InstagramShare", "Image saved to gallery")

                                val file = File(
                                    context.cacheDir,
                                    "instagram_story_image_${System.currentTimeMillis()}.png"
                                )
                                FileOutputStream(file).use { fos ->
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                                }
                                Log.d("InstagramShare", "File absolute path: ${file.absolutePath}")

                                // URI 생성
                                val uri = FileProvider.getUriForFile(
                                    context,
                                    "${context.packageName}.fileprovider",
                                    file
                                )
                                Log.d("InstagramShare", "Generated URI: $uri")

                                // ViewModel에 URI 저장
                                viewModel.setUri(uri.toString())
                                // Instagram 공유
                                shareClicked()
                            } catch (e: Exception) {
                                Log.e("InstagramShare", "Error during URI creation or sharing", e)
                            }
                        }
                        Log.d("InstagramShare", "Button clicked")
                    }
            )
            Spacer(modifier = Modifier.height(27.dp))

        }
    }
}

private fun saveImageToGallery(context: Context, bitmap: Bitmap, fileName: String) {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, fileName) // 파일 이름
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // 파일 형식
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp") // 저장 경로
        put(MediaStore.Images.Media.IS_PENDING, 1) // 저장 중 상태
    }

    val contentResolver = context.contentResolver
    val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    if (uri != null) {
        try {
            contentResolver.openOutputStream(uri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream) // 이미지 저장
            }
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0) // 저장 완료
            contentResolver.update(uri, contentValues, null, null)
        } catch (e: Exception) {
            Log.e("SaveImageToGallery", "Failed to save image to gallery", e)
        }
    } else {
        Log.e("SaveImageToGallery", "Failed to create MediaStore entry")
    }
}


@Preview
@Composable
private fun InstagramCardPreview() {
    InstagramShareScreen("김감자", 1)
}