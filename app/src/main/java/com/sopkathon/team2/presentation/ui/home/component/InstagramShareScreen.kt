package com.sopkathon.team2.presentation.ui.home.component

import InstagramShareViewModel
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.presentation.util.noRippleClickable
import com.sopkathon.team2.presentation.util.roundedBackgroundWithPadding
import com.sopkathon.team2.ui.theme.GAMJATheme
import kotlinx.coroutines.launch
import org.sopt.and.R

@Composable
fun InstagramShareScreen(
    nickname: String,
    level: Int,
    modifier: Modifier = Modifier,
    viewModel: InstagramShareViewModel = viewModel(),
    closeButtonClicked: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val context = androidx.compose.ui.platform.LocalContext.current


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
                            val bitmap = viewModel.captureInstagramCardBitmap(
                                nickname = nickname,
                                level = level,
                                context
                            )
                            viewModel.shareToInstagramStory(context, bitmap)
                        }
                        Log.d("InstagramShare", "Button clicked")
                    }
            )
            Spacer(modifier = Modifier.height(27.dp))

        }
    }
}


@Preview
@Composable
private fun InstagramCardPreview() {
    InstagramShareScreen("김감자", 1)
}