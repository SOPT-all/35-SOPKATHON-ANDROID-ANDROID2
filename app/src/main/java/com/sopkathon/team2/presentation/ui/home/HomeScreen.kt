package com.sopkathon.team2.presentation.ui.home

import HomeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.data.model.response.Users
import com.sopkathon.team2.presentation.ui.bottomsheet.BottomSheetViewModel

import com.sopkathon.team2.presentation.ui.home.component.InstagramShareScreen
import com.sopkathon.team2.presentation.ui.profile.getLevelImage
import com.sopkathon.team2.presentation.util.noRippleClickable
import com.sopkathon.team2.presentation.util.roundedBackgroundWithPadding
import com.sopkathon.team2.presentation.util.showIf
import com.sopkathon.team2.ui.theme.GAMJATheme
import org.sopt.and.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel(),
    onNavigateToProfile: () -> Unit = {},
    onNavigateToWrite: () -> Unit = {},
    onUserClick: (Int) -> Unit = {},
) {
    val userInfo by viewModel.userInfo.collectAsState()
    val shareScreenVisible by viewModel.shareScreenVisible.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }

    val usersInfo = viewModel.usersInfo

    LaunchedEffect(Unit) {
        viewModel.getUsersInfo()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Stupid Potato",
                    style = GAMJATheme.typography.headRegular20,
                    color = GAMJATheme.colors.main
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_home_history),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable {
                        onNavigateToProfile()
                    }
                )

                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_home_friend_list),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable {
                        showBottomSheet = true
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_home_share),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable {
                        viewModel.changeShareScreenVisible()
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${userInfo?.nickName} 님!",
                style = GAMJATheme.typography.headRegular26,
                color = GAMJATheme.colors.white
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "감자라고 낙심하지마세요",
                style = GAMJATheme.typography.headRegular16,
                color = GAMJATheme.colors.white
            )
            Spacer(modifier = Modifier.height(28.dp))

            val levelImage = userInfo?.let { getLevelImage(it.level) }
            levelImage?.let { painterResource(id = it) }?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "감자짓 작성하러가기",
                textAlign = TextAlign.Center,
                style = GAMJATheme.typography.bodyMedium16,
                color = GAMJATheme.colors.gray10,
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedBackgroundWithPadding(
                        padding = PaddingValues(vertical = 15.dp),
                        backgroundColor = GAMJATheme.colors.main,
                        cornerRadius = 50.dp
                    )
                    .noRippleClickable {
                        onNavigateToWrite()
                    }
            )
            Spacer(modifier = Modifier.height(30.dp))

        }

        InstagramShareScreen(nickname = userInfo?.nickName ?: "서버통신 에러",
            level = userInfo?.level ?: -1,
            modifier = Modifier.showIf(shareScreenVisible),
            closeButtonClicked = { viewModel.changeShareScreenVisible() })

    }

    if (showBottomSheet) {
        UserBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            onUserClick = onUserClick,
            userInfo = usersInfo
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(
    viewModel: BottomSheetViewModel = viewModel(),
    onDismissRequest: () -> Unit,
    onUserClick: (Int) -> Unit,
    userInfo: List<Users>
) {
//    LaunchedEffect(Unit) {
//        viewModel.getUsersInfo()
//    }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

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
                        onUserClick = { onUserClick(user.userId)}
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileItem(
    user: Users,
    onUserClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.noRippleClickable { onUserClick(user.userId) }
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
                        1 -> painterResource(R.drawable.gamja1)
                        2 -> painterResource(R.drawable.gamja2)
                        3 -> painterResource(R.drawable.gamja3)
                        4 -> painterResource(R.drawable.gamja4)
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
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow),
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
private fun HomeScreenPreview() {
    HomeScreen()
}
