package com.sopkathon.team2.presentation.ui.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.data.model.response.Users
import com.sopkathon.team2.presentation.util.noRippleClickable
import com.sopkathon.team2.ui.theme.GAMJATheme
import org.sopt.and.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(
    viewModel: BottomSheetViewModel = viewModel(),
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onUserClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getUsersInfo()
    }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val userInfo = viewModel.usersInfo
    val userInfoSize = userInfo.size

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = GAMJATheme.colors.gray10,
    ) {
        Column(
            modifier = Modifier
                .heightIn(max = 684.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "친구 목록",
                color = GAMJATheme.colors.white,
                style = GAMJATheme.typography.titleBold18,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 20.dp),
                textAlign = TextAlign.Center
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 24.dp)
            ) {
                items(
                    items = userInfo,
                    key = { user -> user.userId }
                ) { user ->
                    ProfileItem(
                        user = user,
                        onUserClick = onUserClick
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileItem(
    user: Users,
    onUserClick: () -> Unit
) {
    Column(
        modifier = Modifier.noRippleClickable { onUserClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                Image(
                    painter = when (user.level) {
                        1 -> painterResource(R.drawable.img_dummy)
                        2 -> painterResource(R.drawable.img_dummy)
                        3 -> painterResource(R.drawable.img_dummy)
                        4 -> painterResource(R.drawable.img_dummy)
                        else -> painterResource(R.drawable.img_dummy)
                    },
                    contentDescription = "",
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(45.dp),
                    contentScale = ContentScale.Crop

                )
                Column {
                    Text(
                        text = user.nickName,
                        style = GAMJATheme.typography.bodyMedium16,
                        color = GAMJATheme.colors.white
                    )
                    Text(
                        text = "Lv. ${user.level}",
                        style = GAMJATheme.typography.bodyMedium12,
                        color = GAMJATheme.colors.white
                    )
                }
            }

            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "",
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = GAMJATheme.colors.gray08,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewUserBottomSheet() {
    UserBottomSheet(
        onDismissRequest = {},
        onUserClick = {}
    )
}
