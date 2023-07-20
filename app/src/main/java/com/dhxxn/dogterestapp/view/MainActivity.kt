package com.dhxxn.dogterestapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dhxxn.dogterestapp.ui.theme.DogterestAppTheme
import com.dhxxn.dogterestapp.ui.theme.Red
import com.dhxxn.dogterestapp.view.like.LikeScreen
import com.dhxxn.dogterestapp.view.like.LikeViewModel
import com.dhxxn.dogterestapp.view.list.ListScreen
import com.dhxxn.dogterestapp.view.list.ListViewModel
import com.dhxxn.dogterestapp.view.random.RandomScreen
import com.dhxxn.dogterestapp.view.random.RandomViewModel
import com.dhxxn.dogterestapp.view.search.SearchScreen
import com.dhxxn.dogterestapp.view.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val randomViewModel: RandomViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()
    private val likeViewModel: LikeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogterestAppTheme {
                MainContent()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainContent() {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier,
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { _innerPadding ->
            Box(
                modifier = Modifier.padding(_innerPadding)
            ) {
                BottomNavigationGraph(navController = navController)
            }
        }
    }

    @Composable
    fun BottomNavigationGraph(navController: NavHostController) {
        NavHost(navController = navController, startDestination = BottomNavItem.List.route) {
            composable(BottomNavItem.List.route) {
                ListScreen(listViewModel).CreateContent()
            }
            composable(BottomNavItem.Random.route) {
                RandomScreen(randomViewModel, this@MainActivity).CreateContent()
            }
            composable(BottomNavItem.Like.route) {
                LikeScreen(likeViewModel).CreateContent()
            }
            composable(BottomNavItem.Search.route) {
                SearchScreen(searchViewModel).CreateContent()
            }
        }
    }

    @SuppressLint("ResourceType")
    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        val navItems = listOf<BottomNavItem>(BottomNavItem.List, BottomNavItem.Random, BottomNavItem.Like, BottomNavItem.Search)

        NavigationBar(
            containerColor = Color.White,
            contentColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            navItems.forEach { _item ->
                NavigationBarItem(
                    selected = currentRoute == _item.route,
                    onClick = {
                        navController.navigate(_item.route) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) {saveState = true}
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painterResource(id = _item.icon),
                            contentDescription = stringResource(id = _item.title),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(id = _item.title)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = Color.Black,
                        unselectedIconColor = Color.LightGray,
                        selectedIconColor = Red,
                        unselectedTextColor = Color.LightGray,
                        indicatorColor = Color.White
                    ),
                    alwaysShowLabel = false
                )
            }
        }
    }
}

