package com.dhxxn.dogterestapp.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.ui.theme.BaseTextFieldColors
import com.dhxxn.dogterestapp.ui.theme.Blue
import com.dhxxn.dogterestapp.ui.theme.Typography
import com.dhxxn.dogterestapp.view.base.BaseScreen
import com.dhxxn.dogterestapp.view.list.DogItem
import com.dhxxn.dogterestapp.view.list.StaggeredVerticalGrid
import kotlin.random.Random

class SearchScreen(
    private val viewModel: SearchViewModel
): BaseScreen() {

    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun CreateContent() {
        val state = viewModel.state
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        val breedList = state.tagList.value()
        val colorList = state.colorList.value()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                Modifier.fillMaxWidth()
            ) {
                if (state.screenType.value() == SEARCH_SCREEN_TYPE.RESULT) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 10.dp, top = 15.dp)
                            .size(40.dp)
                            .clickable {
                                viewModel.sendAction(SearchContract.SearchAction.BackToDefault)
                            }
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 10.dp, top = 15.dp)
                            .size(35.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Blue.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(5.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = state.searchKeyword.value(),
                        onValueChange = {
                            viewModel.sendAction(SearchContract.SearchAction.InputKeyword(it))
                        },
                        modifier = Modifier.padding(horizontal = 10.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                viewModel.sendAction(SearchContract.SearchAction.SearchBreed(state.searchKeyword.value()))
                            }
                        ),
                        decorationBox = { _innerTextField ->
                            TextFieldDefaults.TextFieldDecorationBox(
                                value = state.searchKeyword.value(),
                                innerTextField = _innerTextField,
                                enabled = true,
                                singleLine = true,
                                visualTransformation = VisualTransformation.None,
                                interactionSource = MutableInteractionSource(),
                                placeholder = {
                                    Text(
                                        text = "품종을 검색해보세요.",
                                        color = Color.LightGray
                                    )
                                },
                                contentPadding = PaddingValues(0.dp),
                                colors = BaseTextFieldColors()
                            )
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                        )
                    )
                }
            }

            if (state.screenType.value() == SEARCH_SCREEN_TYPE.DEFAULT) {
               Column(
                   horizontalAlignment = Alignment.CenterHorizontally,
                   modifier = Modifier.fillMaxWidth()
               ) {
                   Text(
                       text = "#이런 품종들도 있어요 🐶",
                       fontSize = 17.sp,
                       fontWeight = FontWeight.Bold,
                       color = Color.Black,
                       style = Typography.bodyLarge,
                       modifier = Modifier
                           .padding(top = 80.dp, start = 10.dp)
                   )

                   LazyVerticalGrid(
                       columns = GridCells.Fixed(2),
                       modifier = Modifier
                           .padding(top = 5.dp)
                           .fillMaxSize(),
                       contentPadding = PaddingValues(5.dp),
                       state = rememberLazyGridState()
                   ) {
                       items(7) {
                           val text = breedList[it]
                           val color = colorList[it]

                           Box(
                               modifier = Modifier
                                   .padding(8.dp)
                                   .background(
                                       color = color
                                   )
                                   .padding(30.dp)
                                   .clickable {
                                       viewModel.sendAction(
                                           SearchContract.SearchAction.SearchBreed(
                                               text.first
                                           )
                                       )
                                   },
                               contentAlignment = Alignment.Center,
                           ) {
                               Text(
                                   text = "#${text.second}",
                                   fontSize = 16.sp,
                                   color = Color.White,
                                   fontWeight = FontWeight.Bold,
                                   textAlign = TextAlign.Center
                               )
                           }
                       }
                   }
               }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 80.dp)
                ) {
                    item {
                        StaggeredVerticalGrid(
                            maxColumnWidth = 220.dp,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            viewModel.state.searchResult.value().forEach { _dog ->
                                DogItem(imageUrl = _dog)
                            }
                        }
                    }
                }
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