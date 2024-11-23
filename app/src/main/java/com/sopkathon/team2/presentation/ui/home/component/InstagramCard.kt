package com.sopkathon.team2.presentation.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun InstagramCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(20.dp), color = Color(0xFF474747)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(60.dp))
        Text(text = "김감자 님", color = Color.White)
        Spacer(modifier = modifier.height(16.dp))
        Text(text = "슈퍼감자 Lv.3", color = Color.White)
        Spacer(modifier = modifier.height(42.dp))
        Image(
            painter = painterResource(R.drawable.img_dummy),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color(0xFF252528))
        )
        Spacer(modifier = modifier.height(47.dp))

    }
}

@Preview
@Composable
private fun InstagramCardPreview() {
    InstagramCard()
}