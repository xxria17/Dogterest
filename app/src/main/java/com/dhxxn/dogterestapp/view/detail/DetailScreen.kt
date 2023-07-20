package com.dhxxn.dogterestapp.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.view.base.BaseScreen
import dagger.hilt.android.AndroidEntryPoint


class DetailScreen(
    private val viewModel: DetailViewModel
): BaseScreen() {

    @Composable
    override fun CreateContent() {
        Column() {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = ""
                )

                AsyncImage(model = "", contentDescription = "")
            }


        }
    }


}