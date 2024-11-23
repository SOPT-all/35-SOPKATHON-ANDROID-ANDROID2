package com.sopkathon.team2.presentation.ui.home.component

import InstagramShareViewModel
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.presentation.util.noRippleClickable
import com.sopkathon.team2.presentation.util.roundedBackgroundWithPadding
import kotlinx.coroutines.launch


@Composable
fun InstagramShareScreen(
    modifier: Modifier = Modifier,
    viewModel: InstagramShareViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InstagramCard()
        Spacer(modifier = modifier.height(28.dp))
        Text(
            text = "인스타그램 공유하기",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .roundedBackgroundWithPadding(
                    padding = PaddingValues(
                        vertical = 15.dp,
                    ), cornerRadius = 10.dp, backgroundColor = Color(0xFF474747)
                )
                .noRippleClickable {
                    scope.launch {
                        val bitmap = viewModel.captureInstagramCardBitmap(context)
                        viewModel.shareToInstagramStory(context, bitmap)
                    }
                    Log.d("InstagramShare", "Button clicked")
                })
    }
}


@Preview
@Composable
private fun InstagramCardPreview() {
    InstagramShareScreen()
}