package com.dhxxn.dogterestapp.view.like

import android.widget.Toast
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.view.base.BaseScreen
import com.dhxxn.dogterestapp.view.list.DogItem
import com.dhxxn.dogterestapp.view.list.StaggeredVerticalGrid
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class LikeScreen (
    private val viewModel: LikeViewModel
): BaseScreen() {

    @Composable
    fun Effect() {
        val context = LocalContext.current

        LaunchedEffect(viewModel.effect) {
            viewModel.effect.onEach { _effect ->
                when (_effect) {
                    is LikeContract.LikeEffect.ShowToast -> {
                        Toast.makeText(context, _effect.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }.collect()
        }
    }

    @Composable
    override fun CreateContent() {
        Effect()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp)
                        .size(30.dp)
                )
            }
            LazyColumn(
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                item {
                    StaggeredVerticalGrid(
                        maxColumnWidth = 220.dp,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        viewModel.state.likeImgList.value().forEach { _dog ->
                            DogItem(imageUrl = _dog.imageUrl)
                        }
                    }
                }
            }
        }
    }

}