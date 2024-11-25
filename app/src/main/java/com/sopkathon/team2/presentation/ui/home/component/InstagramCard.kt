package com.sopkathon.team2.presentation.ui.home.component

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopkathon.team2.presentation.ui.profile.getLevelImage
import com.sopkathon.team2.ui.theme.GAMJATheme

@Composable
fun InstagramCard(nickname: String, level: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(20.dp), color = GAMJATheme.colors.main),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "$nickname 님",
            color = GAMJATheme.colors.black,
            style = GAMJATheme.typography.headRegular26
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "슈퍼감자 Lv.$level",
            color = GAMJATheme.colors.black,
            style = GAMJATheme.typography.headRegular16
        )
        Spacer(modifier = Modifier.height(42.dp))

        val levelImage = getLevelImage(level)
        levelImage?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                        brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0.15f to Color(0xFF121413),
                                1.0f to Color(0xFF241705)
                            ),
                        )
                    )
            )
        }

    }
}

@Preview
@Composable
private fun InstagramCardPreview() {
    InstagramCard("김감자", 1)
}