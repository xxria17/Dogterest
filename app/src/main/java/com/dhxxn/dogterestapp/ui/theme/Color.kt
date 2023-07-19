package com.dhxxn.dogterestapp.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Blue = Color(0xff1d67c2)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTextFieldColors(
    text: Color = Color.White,
    disabledColor: Color = Color.Gray,
    cursorColor: Color = Blue,
    errorCursor: Color = Color.Red,
    containerColor: Color = Color.Transparent,
    placeholderTextColor: Color = Color.LightGray
) = TextFieldDefaults.textFieldColors(
    textColor = text,
    disabledTextColor = disabledColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursor,
    containerColor = containerColor,
    placeholderColor = placeholderTextColor,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)