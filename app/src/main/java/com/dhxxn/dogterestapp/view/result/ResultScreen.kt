package com.dhxxn.dogterestapp.view.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.view.list.DogItem
import com.dhxxn.dogterestapp.view.list.StaggeredVerticalGrid

@Composable
fun ResultScreen(
    resultList: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFFFF6F00), shape = RoundedCornerShape(5.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(25.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )

            Text(
                text = "품종별 검색하기",
                modifier = Modifier.padding(start = 15.dp),
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn {
            item {
                StaggeredVerticalGrid(
                    maxColumnWidth = 220.dp,
                    modifier = Modifier.padding(4.dp)
                ) {
                    resultList.forEach { _dog ->
                        DogItem(imageUrl = _dog)
                    }
                }
            }
        }
    }
}