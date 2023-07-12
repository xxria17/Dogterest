package com.dhxxn.dogterestapp.view.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
@Preview
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            item {
                StaggeredVerticalGrid(
                    maxColumnWidth = 220.dp,
                    modifier = Modifier.padding(4.dp)
                ) {
                    viewModel.state.imageList.forEach { _dog ->
                        DogItem(imageUrl = _dog)
                    }
                }
            }
        }
    }
}