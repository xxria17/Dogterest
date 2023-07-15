package com.dhxxn.dogterestapp.view.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.ui.theme.Typography
import com.dhxxn.dogterestapp.view.base.BaseScreen

class RandomScreen(
    private val viewModel: RandomViewModel
): BaseScreen() {

    @Composable
    override fun CreateContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp).size(30.dp)
                )
            }

            Column(
                modifier = Modifier.padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "오늘의 개",
                    fontSize = 35.sp,
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 40.dp),
                    color = Color.Black
                )

                AsyncImage(
                    model = viewModel.state.randomImg.value(),
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.picture),
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = Color(0xFFB3E7FF)
                        )
                        .padding(10.dp)
                        .clickable {
                            viewModel.sendAction(RandomContract.RandomAction.RequestNewImage)
                        }
                ) {
                    Text(
                        text = "다른 개 보기",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}