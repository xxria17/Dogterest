package com.dhxxn.dogterestapp.view.random

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RandomScreen(
        viewModel: RandomViewModel
) {
    var temp by  remember { mutableStateOf("") }

    LaunchedEffect(true) {
        Log.d("!!!!!!", "requestRandomData")
        viewModel.requestRandomDogData()
    }

    LaunchedEffect(viewModel.randomDog) {
        viewModel.randomDog.collectLatest { state ->
            temp = state?.imageUrl ?: ""
        }
    }

    Log.d("!!!!!!", "dog image :: ${viewModel.randomDog.value?.imageUrl ?: "nothing"}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "오늘의 개",
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 40.dp),
                color = Color(0xff584630)
            )

            AsyncImage(
                model = temp,
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xff584630),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)
                    .clickable {
                        viewModel.requestRandomDogData()
                    }
            ) {
                Text(
                    text = "다른 개 보기",
                    fontSize = 20.sp,
                    color = Color(0xff584630)
                )
            }
        }
    }
}