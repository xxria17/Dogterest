package com.dhxxn.dogterestapp.view.random

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dhxxn.dogterestapp.view.BottomMenuSection

@Composable
@Preview
fun RandomScreen(
    //    viewModel: ListViewModel = hiltViewModel()
) {
    val temp = "https://images.dog.ceo/breeds/maltese/n02085936_9310.jpg"

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
                modifier = Modifier.size(300.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier.padding(top = 40.dp)
                    .border(width = 2.dp, color = Color(0xff584630), shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
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