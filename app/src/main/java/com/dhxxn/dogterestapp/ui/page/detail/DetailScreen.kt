package com.dhxxn.dogterestapp.ui.page.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.ui.base.BaseScreen
import dagger.hilt.android.AndroidEntryPoint

class DetailScreen(
    private val viewModel: DetailViewModel,
    private val imageUrl: String
): BaseScreen() {

    @Composable
    override fun CreateContent() {
        Column() {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = ""
                )

                AsyncImage(model = imageUrl, contentDescription = "Detail Image")
            }


        }
    }


}