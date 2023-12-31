package com.dhxxn.dogterestapp.ui.page.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.ui.base.BaseScreen
import com.dhxxn.dogterestapp.ui.navigation.Screens
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class ListScreen(
    private val viewModel: ListViewModel,
    private val navController: NavController
): BaseScreen() {

    @Composable
    override fun CreateContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
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
            LazyColumn {
                item {
                    StaggeredVerticalGrid(
                        maxColumnWidth = 220.dp,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        viewModel.state.imageList.value().forEach { _dog ->
                            DogItem(
                                imageUrl = _dog,
                                onClick = { _imageUrl ->
                                    val encodedUrl = URLEncoder.encode(_imageUrl , StandardCharsets.UTF_8.toString())
                                    navController.navigate(
                                        Screens.DetailScreen.withImageUrl(
                                            encodedUrl
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}