package com.dhxxn.dogterestapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dhxxn.dogterestapp.R

@Composable
fun BoxScope.BottomMenuSection(
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .height(50.dp)
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    onClick.invoke(0)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_random),
            contentDescription = "",
            modifier = Modifier
                .size(33.dp)
                .clickable {
                    onClick.invoke(1)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "",
            modifier = Modifier
                .size(27.dp)
                .clickable {
                    onClick.invoke(2)
                }
        )
    }
}