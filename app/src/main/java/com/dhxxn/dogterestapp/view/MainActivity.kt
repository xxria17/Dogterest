package com.dhxxn.dogterestapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dhxxn.dogterestapp.ui.theme.DogterestAppTheme
import com.dhxxn.dogterestapp.view.list.ListScreen
import com.dhxxn.dogterestapp.view.list.ListViewModel
import com.dhxxn.dogterestapp.view.random.RandomScreen
import com.dhxxn.dogterestapp.view.random.RandomViewModel
import com.dhxxn.dogterestapp.view.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val randomViewModel : RandomViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogterestAppTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        var selectedIndex by remember {
            mutableStateOf(0)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            when(selectedIndex) {
                0 -> ListScreen(listViewModel)
                1 -> RandomScreen(randomViewModel)
                else -> SearchScreen()
            }

            BottomMenuSection(
                onClick = {
                    selectedIndex = it
                }
            )
        }
    }
}

