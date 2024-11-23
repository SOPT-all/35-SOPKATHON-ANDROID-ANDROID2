package com.sopkathon.team2.presentation.ui.write

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun WriteScreen(
    viewModel: WriteViewModel = viewModel()
) {
    val text = viewModel.text

    Scaffold(
        bottomBar = {
            WriteBottomBar(
                onClick = {}
            )
        },
        content = { innerPadding ->
            WriteScreenContent(
                modifier = Modifier.padding(innerPadding),
                text = text,
                onChangedTextField = { viewModel.onChangedTextField(it) },
                textSize = viewModel.getTextSize(),
            )
        }
    )
}

@Composable
private fun WriteScreenContent(
    modifier: Modifier = Modifier,
    text: String,
    onChangedTextField: (String) -> Unit,
    textSize: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "오늘 했던 \n감자짓을 알려주세요.",
            modifier = Modifier.padding(top = 104.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        AddPictureItem()

        Spacer(modifier = Modifier.height(31.dp))

        WriteTextField(
            text = text,
            onChangedTextField = onChangedTextField,
            textSize = textSize
        )
    }

}

@Composable
fun AddPictureItem(
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
    }

    if (selectedImageUri == null) {
        Column(
            modifier = Modifier
                .clickable { launcher.launch("image/*") }
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(6.dp)
                )
                .height(207.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add photo"
            )
            Text(
                text = "사진등록"
            )
        }
    } else {
        Image(
            painter = rememberAsyncImagePainter(model = selectedImageUri),
            contentDescription = "Selected Image",
            modifier = Modifier
                .height(207.dp)
                .fillMaxWidth()
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(6.dp)
                )
                .clickable { launcher.launch("image/*") },
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun WriteTextField(
    text: String,
    onChangedTextField: (String) -> Unit,
    textSize: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicTextField(
            value = text,
            onValueChange = onChangedTextField,
            modifier = Modifier
                .fillMaxWidth()
                .height(171.dp)
                .wrapContentWidth(),
        ) {
            if (text.isEmpty()) {
                Text(
                    text = "오늘 한 감자짓을 적어주세요!",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = text,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = " $textSize /270",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
private fun WriteBottomBar(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = 10.dp,
                horizontal = 24.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .background(
                    color = Color.Red,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "완료",
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewWriteScreen() {
    WriteScreen()
}
