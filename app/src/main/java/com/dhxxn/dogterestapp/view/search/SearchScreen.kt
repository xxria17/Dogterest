package com.dhxxn.dogterestapp.view.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.view.base.BaseScreen
import com.dhxxn.dogterestapp.view.result.ResultScreen
import kotlin.random.Random

class SearchScreen(
    private val viewModel: SearchViewModel
): BaseScreen() {

    @Composable
    override fun CreateContent() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 80.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color(0xff1d67c2).copy(alpha = 0.6f), shape = RoundedCornerShape(5.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(25.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )

                BasicTextField(
                    value = viewModel.state.searchKeyword.value(),
                    onValueChange = {
                        viewModel.sendAction(SearchContract.SearchAction.InputKeyword(it))
                    },
                    modifier = Modifier
                )
            }

            val breedList = viewModel.state.tagList.value()
            var type by remember {
                mutableStateOf(0)
            }

            if (type == 0) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(top = 80.dp)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(5.dp),
                    state = rememberLazyGridState()
                ) {
                    items(10) {
                        val text = breedList[it]

                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    color = Color.Companion
                                        .random()
                                        .copy(alpha = 0.8f)
                                )
                                .padding(25.dp)
                                .clickable {
                                    type = 1
                                    viewModel.sendAction(SearchContract.SearchAction.ClickBreed(text))
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "#$text",
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                ResultScreen(resultList = viewModel.state.searchResult.value())
            }
        }
    }
}

fun Color.Companion.random() : Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

enum class SearchRoute {
    Search,
    Result
}