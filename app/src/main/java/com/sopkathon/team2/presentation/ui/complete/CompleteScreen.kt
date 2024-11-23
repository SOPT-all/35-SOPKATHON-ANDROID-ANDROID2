package com.sopkathon.team2.presentation.ui.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.presentation.util.noRippleClickable
import com.sopkathon.team2.ui.theme.Black
import com.sopkathon.team2.ui.theme.Gray09
import com.sopkathon.team2.ui.theme.Gray10
import com.sopkathon.team2.ui.theme.Main
import com.sopkathon.team2.ui.theme.Sub
import com.sopkathon.team2.ui.theme.White
import com.sopkathon.team2.ui.theme.defaultGAMJATypography
import org.sopt.and.R

@Composable
fun CompleteScreen(
    onNavigationToHome: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: CompleteViewModel = viewModel()
) {
    LaunchedEffect(true) {
        viewModel.getPotatoInfo(1)
    }

    val potato by viewModel.potato.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.15f to Color(0xFF121413),
                        1.0f to Color(0xFF241705)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 88.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompleteCard(potato?.data?.content.orEmpty())
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "인스타 공유하기",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Sub)
                        .noRippleClickable { }
                        .padding(vertical = 11.5.dp),
                    color = Gray09,
                    style = defaultGAMJATypography.bodyMedium16
                )
                // 완료 버튼
                Text(
                    text = "완료",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Main)
                        .noRippleClickable(onNavigationToHome)
                        .padding(vertical = 11.5.dp),
                    color = Black,
                    style = defaultGAMJATypography.bodyMedium16
                )
            }
        }
    }
}



@Composable
fun CompleteCard(
    content : String,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(Gray10)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(2.4f)
        )
        Column(
            modifier = Modifier.padding(12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1.44f)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
            )
            Text(
                text = content,
                modifier = Modifier.padding(8.dp),
                style = defaultGAMJATypography.bodyMedium12,
                color = White
            )
        }
    }
}