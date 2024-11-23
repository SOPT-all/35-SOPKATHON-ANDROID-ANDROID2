package com.sopkathon.team2.presentation.ui.write

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.sopkathon.team2.ui.theme.GAMJATheme
import org.sopt.and.R

@Composable
fun WriteScreen(
    viewModel: WriteViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onNavigateToComplete: () -> Unit = {}
) {
    val text = viewModel.text
    val imageUri = viewModel.imageUri

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.onChangedImage(uri)
    }

    WriteScreenContent(
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
            ),
        text = text,
        onChangedTextField = { viewModel.onChangedTextField(it) },
        textSize = viewModel.getTextSize(),
        imageUri = imageUri,
        onClick = { launcher.launch("image/*") },
        onCompleteClick = {
            viewModel.postContent()
            onNavigateToComplete()
        }
    )
}

@Composable
private fun WriteScreenContent(
    modifier: Modifier = Modifier,
    text: String,
    onChangedTextField: (String) -> Unit,
    textSize: String,
    imageUri: Uri?,
    onClick: () -> Unit,
    onCompleteClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "오늘 했던 \n감자짓을 알려주세요.",
            style = GAMJATheme.typography.headRegular20,
            color = GAMJATheme.colors.white,
            modifier = Modifier.padding(top = 104.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        AddPictureItem(
            imageUri = imageUri,
            onClick = onClick
        )

        Spacer(modifier = Modifier.height(31.dp))

        WriteTextField(
            text = text,
            onChangedTextField = onChangedTextField,
            textSize = textSize
        )

        Spacer(modifier = Modifier.height(133.dp))

        WriteBottomBar(
            onClick = onCompleteClick
        )
    }

}

@Composable
fun AddPictureItem(
    imageUri: Uri?,
    onClick: () -> Unit
) {
    if (imageUri == null) {
        Column(
            modifier = Modifier
                .clickable { onClick() }
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(6.dp)
                )
                .drawBehind {
                    val pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(10f, 10f),
                        0f
                    )

                    drawRoundRect(
                        color = Color.DarkGray,
                        size = size,
                        cornerRadius = CornerRadius(6.dp.toPx()),
                        style = Stroke(
                            width = 1.dp.toPx(),
                            pathEffect = pathEffect
                        )
                    )
                }
                .height(134.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_plus),
                contentDescription = "Add photo",
                tint = GAMJATheme.colors.gray07,
            )
            Text(
                text = "사진을 등록해주세요",
                style = GAMJATheme.typography.bodyMedium12,
                color = GAMJATheme.colors.gray07,
            )
        }
    } else {
        Image(
            painter = rememberAsyncImagePainter(model = imageUri),
            contentDescription = "Selected Image",
            modifier = Modifier
                .height(207.dp)
                .fillMaxWidth()
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(6.dp)
                )
                .clickable { onClick() },
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

    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (isFocused) GAMJATheme.colors.main else GAMJATheme.colors.gray10

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = GAMJATheme.colors.gray10,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
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
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .wrapContentWidth(),
        ) {
            if (text.isEmpty()) {
                Text(
                    text = "오늘 한 감자짓을 적어주세요!",
                    style = GAMJATheme.typography.bodyMedium12,
                    color = GAMJATheme.colors.gray05,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = text,
                style = GAMJATheme.typography.bodyMedium12,
                color = GAMJATheme.colors.white,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = " $textSize /270",
                    style = GAMJATheme.typography.bodyMedium12,
                    color = GAMJATheme.colors.gray07
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .background(
                    color = GAMJATheme.colors.main,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "완료",
                style = GAMJATheme.typography.bodyMedium16,
                color = GAMJATheme.colors.black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewWriteScreen() {
    WriteScreen()
}
