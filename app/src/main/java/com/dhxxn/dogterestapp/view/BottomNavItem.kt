package com.dhxxn.dogterestapp.view

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import com.dhxxn.dogterestapp.R

sealed class BottomNavItem(
    @IdRes val title: Int,
    @IdRes val icon: Int,
    val route: String
) {
    @SuppressLint("ResourceType")
    object List : BottomNavItem(title = R.string.list_screen, icon = R.drawable.ic_home, route = "LIST")

    @SuppressLint("ResourceType")
    object Random : BottomNavItem(title = R.string.randomg_screen, icon = R.drawable.ic_random, route = "RANDOM")

    @SuppressLint("ResourceType")
    object Like : BottomNavItem(title = R.string.like_screen, icon = R.drawable.ic_heart, route = "LIKE")

    @SuppressLint("ResourceType")
    object Search : BottomNavItem(title = R.string.search_screen, icon = R.drawable.ic_search, route = "SEARCH")
}
