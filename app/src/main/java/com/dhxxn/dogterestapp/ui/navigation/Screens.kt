package com.dhxxn.dogterestapp.ui.navigation

const val IMAGE_URL_ARG = "imageUrl"

sealed class Screens(val route: String) {

    object DetailScreen : Screens("detail_screen/{$IMAGE_URL_ARG}") {
        fun withImageUrl(imageUrl: String): String {
            return this.route.replace(IMAGE_URL_ARG, imageUrl)
        }
    }

}