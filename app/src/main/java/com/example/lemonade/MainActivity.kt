package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Yellow,
                    titleContentColor = Color.Gray,
                    navigationIconContentColor = Color.Magenta,
                    actionIconContentColor = Color.Blue
                )
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (currentStep) {
                1 -> {
                    LemonadePicWithText(
                        textId = R.string.first,
                        stringId = R.string.first,
                        drawableId = R.drawable.lemon_tree,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                2 -> {
                    LemonadePicWithText(
                        textId = R.string.second,
                        stringId = R.string.second,
                        drawableId = R.drawable.lemon_squeeze,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonadePicWithText(
                        textId = R.string.third,
                        stringId = R.string.third,
                        drawableId = R.drawable.lemon_drink,
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }
                4 ->{
                    LemonadePicWithText(
                        textId = R.string.fourth,
                        stringId = R.string.fourth,
                        drawableId = R.drawable.lemon_restart,
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadePicWithText(
    modifier: Modifier = Modifier,
    textId: Int,
    stringId: Int,
    drawableId: Int,
    onImageClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                onImageClick()
            },
            shape = RoundedCornerShape(8)
        ) {
            Image(
                painter = painterResource(drawableId),
                contentDescription = stringResource(id = textId),
                modifier = modifier
                    .padding(25.dp)
            )
        }
        Text(
            text = stringResource(id = stringId),
            fontSize = 16.sp,
            modifier = modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
//        LemonadePicWithText()
    }
}