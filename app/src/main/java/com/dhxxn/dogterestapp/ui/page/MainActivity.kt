package com.dhxxn.dogterestapp.ui.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dhxxn.dogterestapp.ui.theme.DogterestAppTheme
import com.dhxxn.dogterestapp.ui.theme.Red
import com.dhxxn.dogterestapp.ui.page.like.LikeScreen
import com.dhxxn.dogterestapp.ui.page.like.LikeViewModel
import com.dhxxn.dogterestapp.ui.page.list.ListScreen
import com.dhxxn.dogterestapp.ui.page.list.ListViewModel
import com.dhxxn.dogterestapp.ui.page.random.RandomScreen
import com.dhxxn.dogterestapp.ui.page.random.RandomViewModel
import com.dhxxn.dogterestapp.ui.page.search.SearchScreen
import com.dhxxn.dogterestapp.ui.page.search.SearchViewModel
import com.dhxxn.dogterestapp.ui.navigation.BottomNavItem
import com.dhxxn.dogterestapp.ui.navigation.IMAGE_URL_ARG
import com.dhxxn.dogterestapp.ui.navigation.Screens
import com.dhxxn.dogterestapp.ui.page.detail.DetailScreen
import com.dhxxn.dogterestapp.ui.page.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val randomViewModel: RandomViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()
    private val likeViewModel: LikeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()

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
        var showBottomBar by rememberSaveable {
            mutableStateOf(true)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when(navBackStackEntry?.destination?.route) {
            Screens.DetailScreen.route -> false
            else -> true
        }

        Scaffold(
            modifier = Modifier,
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(navController = navController)
                }
            }
        ) { _innerPadding ->
            Box(
                modifier = Modifier.padding(_innerPadding)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController = navController, startDestination = BottomNavItem.List.route) {
            composable(BottomNavItem.List.route) {
                ListScreen(listViewModel, navController).CreateContent()
            }
            composable(BottomNavItem.Random.route) {
                RandomScreen(randomViewModel, this@MainActivity).CreateContent()
            }
            composable(BottomNavItem.Like.route) {
                LikeScreen(likeViewModel, navController).CreateContent()
            }
            composable(BottomNavItem.Search.route) {
                SearchScreen(searchViewModel, navController).CreateContent()
            }

            composable(
                route = Screens.DetailScreen.route,
                arguments = listOf(
                    navArgument(IMAGE_URL_ARG) {
                        type = NavType.StringType
                    }
                )
            ) { _backStackEntry ->
                val imageUrl = _backStackEntry.arguments!!.getString(IMAGE_URL_ARG)!!
                DetailScreen(detailViewModel, imageUrl, navController).CreateContent()
            }
        }
    }

    @SuppressLint("ResourceType")
    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        val navItems = listOf<BottomNavItem>(
            BottomNavItem.List,
            BottomNavItem.Random,
            BottomNavItem.Like,
            BottomNavItem.Search
        )

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

