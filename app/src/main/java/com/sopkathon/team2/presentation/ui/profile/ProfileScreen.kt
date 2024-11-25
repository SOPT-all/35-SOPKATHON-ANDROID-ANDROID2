package com.sopkathon.team2.presentation.ui.profile

import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.data.model.response.ResponseProfileDto
import com.sopkathon.team2.presentation.ui.profile.component.GamjaJitItem
import com.sopkathon.team2.ui.theme.GAMJATheme
import com.sopkathon.team2.ui.theme.Gray11
import com.sopkathon.team2.ui.theme.White
import org.sopt.and.R


@Composable
fun getLevelImage(level: Int): Int {
    return when (level) {
        1 -> R.drawable.gamja1
        2 -> R.drawable.gamja2
        3 -> R.drawable.gamja3
        4 -> R.drawable.gamja4
        5-> R.drawable.gamja5
        else -> {
            R.drawable.img_dummy
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onNavigateToHome: () -> Unit, userId: Long) {
    val profileViewModel: ProfileViewModel = viewModel()
    val response by profileViewModel.response.collectAsState()
    val userResponse by profileViewModel.userResponse.collectAsState()

    LaunchedEffect(Unit) {
        Log.e("ㅋㅋ", userId.toString())

        profileViewModel.loadProfiles(userId)
        profileViewModel.loadUser(userId)
    }

    if (response == null) {
        Text("로딩중..")
    } else {
        when (response!!.body()?.status) {
            200 -> {
                if (userId == 1L) {
                    MyViewScreen(response!!.body())
                } else {
                    FriendViewScreen(response!!.body(), level = userResponse!!.body()?.data!!.level, nickname =userResponse!!.body()?.data!!.nickName )
                }
            }

            else -> {
                response!!.body()?.let { Text(text = it.message) }
            }
        }
    }



}

@Composable
fun MyViewScreen(data: ResponseProfileDto?) {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.15f to Color(0xFF121413),
                        1.0f to Color(0xFF241705)
                    )
                )
            )
            .fillMaxSize(),
    ) {
        Text(
            text = "나는야감자" + "님의 감자짓",
            color = White,
            style = GAMJATheme.typography.headRegular20,
            modifier = Modifier
                .padding(top = 104.dp, start = 30.dp, bottom = 12.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            data?.data?.let { boards ->
                items(boards) { board ->
                    GamjaJitItem(
                        image = board.image,
                        content = board.content,
                        date = board.createdAt,
                    )
                }
            }
        }
    }
}

@Composable
fun FriendViewScreen(data: ResponseProfileDto?, level: Int,nickname: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.15f to Color(0xFF121413),
                        1.0f to Color(0xFF241705)
                    )
                )
            ),
        contentPadding = PaddingValues(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 94.dp)
            ) {
                Text(
                    text = "$nickname 님",
                    color = White,
                    style = GAMJATheme.typography.headRegular26,
                )
                Text(
                    text = "Lv.$level",
                    color = White,
                    style = GAMJATheme.typography.headRegular16,
                    modifier = Modifier.padding(top = 7.dp, bottom = 34.dp)
                )

                val levelImage = getLevelImage(level)
                Image(
                    painter = painterResource(id = levelImage),
                    contentDescription = null,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }
        data?.data?.let { boards ->
            items(boards) { board ->
                GamjaJitItem(
                    image = board.image,
                    content = board.content,
                    date = board.createdAt,
                )
            }
        }
    }
}
