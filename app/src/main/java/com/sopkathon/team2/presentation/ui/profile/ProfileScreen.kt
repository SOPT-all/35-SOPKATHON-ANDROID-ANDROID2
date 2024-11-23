package com.sopkathon.team2.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopkathon.team2.data.model.response.ResponseProfileDto
import com.sopkathon.team2.presentation.ui.profile.component.GamjaJitItem
import com.sopkathon.team2.ui.theme.GAMJATheme
import com.sopkathon.team2.ui.theme.Gray11
import com.sopkathon.team2.ui.theme.White

@Composable
fun ProfileScreen(userId: Long) {
    val profileViewModel: ProfileViewModel = viewModel()
    val response by profileViewModel.response.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.loadProfiles(userId)
    }

    if (response == null) {
        Text("로딩~중~")
    } else {
        when (response!!.body()?.status) {
            200 -> {
                if (userId == 1L) {
                    MyViewScreen(response!!.body())
                } else {
                    FriendViewScreen(response!!.body())
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
            .background(color = Gray11)
            .fillMaxSize(),
    ) {
        Text(
            text = "나는야감자" + "님의 감자짓",
            color = White,
            style = GAMJATheme.typography.headRegular20,
            modifier = Modifier
                .padding(top = 104.dp, start = 30.dp, bottom = 12.dp)
        )
        if (data != null) {
            data.data?.forEach { board ->
                GamjaJitItem(
                    image = board.image,
                    content = board.content,
                    date = board.createdAt,
                )
            }
        }
    }
}

@Composable
fun FriendViewScreen(data: ResponseProfileDto?) {
    Column (
        modifier = Modifier
            .background(color = Gray11)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "나는야감자" + "님",
            color = White,
            style = GAMJATheme.typography.headRegular26,
            modifier = Modifier
                .padding(top = 94.dp)
        )
        Text(
            text = "Lv." + "",
            color = White,
            style = GAMJATheme.typography.headRegular16,
            modifier = Modifier
                .padding(top = 7.dp, bottom = 34.dp)
        )

        if (data != null) {
            data.data?.forEach { board ->
                GamjaJitItem(
                    image = board.image,
                    content = board.content,
                    date = board.createdAt,
                )
            }
        }
    }
}
