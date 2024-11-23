package com.sopkathon.team2.presentation.ui.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sopkathon.team2.ui.theme.GAMJATheme
import com.sopkathon.team2.ui.theme.Gray07
import com.sopkathon.team2.ui.theme.Gray09
import com.sopkathon.team2.ui.theme.White

@Composable
fun GamjaJitItem(
    image: String?,
    content: String,
    date: String,
) {
    val isExpanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .background(color = Gray09, shape = RoundedCornerShape(11.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        image?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(288f / 200f)
                    .clip(RoundedCornerShape(6.dp))
            ) {

                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Text(
            text = content,
            color = White,
            style = GAMJATheme.typography.bodyMedium12,
            maxLines = if (isExpanded.value) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .clickable { isExpanded.value = !isExpanded.value }
                .padding(top = 10.dp)
        )

        Text(
            text = date,
            color = Gray07,
            fontSize = 12.sp,
            lineHeight = 19.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp)
        )
    }
    Spacer(modifier = Modifier.padding(bottom = 20.dp))
}