package com.dhxxn.dogterestapp.view.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.view.BottomMenuSection

@Composable
@Preview
fun ListScreen(
//    viewModel: ListViewModel = hiltViewModel()
) {
    val tempList = mutableListOf<String>(
        "https://images.dog.ceo/breeds/maltese/n02085936_10073.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10130.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10148.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10197.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10199.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10273.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10297.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10307.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10377.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10397.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10447.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10610.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10625.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10646.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10661.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_10719.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_11541.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1155.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_11653.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_11744.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1244.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1247.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_126.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1288.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_13013.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1325.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_13378.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_137.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1390.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1424.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_14432.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_14595.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1515.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1549.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1556.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_16014.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_16188.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_16190.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_16331.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_16355.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_16565.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_16998.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_17059.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_1739.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_17631.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_18114.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_18640.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_19133.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_19558.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_19731.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_19805.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_20076.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_20102.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2020.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_20313.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_20610.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_21320.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2135.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2203.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_22167.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_22439.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_233.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_24178.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2422.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2458.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2465.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2536.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2543.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2573.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2636.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2693.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2741.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2760.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2832.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2889.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2905.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2927.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_296.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_2976.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3000.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3108.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3207.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3239.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3313.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3315.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3326.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3348.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_338.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3391.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3394.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3396.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3451.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3470.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_352.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3580.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3677.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3678.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3698.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_37.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3947.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_3994.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4004.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4070.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4188.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4192.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_420.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4245.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4257.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_426.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4262.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4271.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4318.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4343.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4351.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4396.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4474.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4480.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4490.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4506.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4569.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4608.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_461.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4695.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4713.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4781.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4894.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4916.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4921.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4924.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_4929.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_500.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5010.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5012.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5023.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5068.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5103.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5107.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_517.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5202.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5250.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5404.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5435.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_544.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5459.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_548.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5488.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5545.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5582.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5596.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5645.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5728.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5766.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_5789.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6034.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6071.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6077.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6278.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6348.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6405.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6424.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6464.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6514.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6569.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_66.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6650.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6656.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6671.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6746.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6892.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6921.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6927.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_6942.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7004.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7142.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7172.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_719.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7198.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7253.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7272.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7311.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7345.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7364.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7394.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7442.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7465.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7515.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7537.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7755.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7793.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7865.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_7941.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_797.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_804.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_807.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8089.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8224.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8248.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8350.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8365.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8417.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8447.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8449.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_850.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8507.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_851.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8616.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8735.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8745.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8756.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8767.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8867.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_8981.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_899.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9037.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9088.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9136.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9141.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9270.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9310.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9590.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9632.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9742.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9812.jpg",
        "https://images.dog.ceo/breeds/maltese/n02085936_9927.jpg",
        "https://images.dog.ceo/breeds/maltese/n20201012_img_0437.jpg"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.padding(bottom = 50.dp)
        ) {
            item {
                StaggeredVerticalGrid(
                    maxColumnWidth = 220.dp,
                    modifier = Modifier.padding(4.dp)
                ) {
                    tempList.forEach { _dog ->
                        DogItem(imageUrl = _dog)
                    }
                }
            }
        }
    }
}