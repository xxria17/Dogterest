package com.dhxxn.dogterestapp.ui.page.detail

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.ui.base.BaseScreen
import com.dhxxn.dogterestapp.ui.theme.Red
import com.dhxxn.dogterestapp.ui.theme.Typography
import com.dhxxn.dogterestapp.ui.theme.Yellow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Date

class DetailScreen(
    private val viewModel: DetailViewModel,
    private val imageUrl: String,
    private val navController: NavController
): BaseScreen() {

    @Composable
    fun Effect() {
        val context = LocalContext.current

        LaunchedEffect(viewModel.effect) {
            viewModel.effect.onEach { _effect ->
                when(_effect) {
                    is DetailContract.Effect.DownloadDevice -> {
                        val fileName = "/Dogterest/${SimpleDateFormat("yyyMMddHHmmss").format(Date())}.jpg"

                        val req = DownloadManager.Request(Uri.parse(_effect.imageUrl))
                        req.setTitle(fileName)
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
                            .setMimeType("image/*")
                            .setDestinationInExternalPublicDir(
                                Environment.DIRECTORY_PICTURES,
                                fileName
                            )

                        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        manager.enqueue(req)

                        Toast.makeText(context, "저장되었습니다 !", Toast.LENGTH_SHORT).show()
                    }
                    is DetailContract.Effect.ShowToast -> {
                        Toast.makeText(context, _effect.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }.collect()
        }
    }

    @Composable
    override fun CreateContent() {
        Effect()

        val image = imageUrl.replace("{", "").replace("}", "")
        val breed = image.split("/")[4]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                AsyncImage(
                    model = image,
                    contentDescription = "Detail Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 500.dp),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .size(40.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }

            Text(
                text = "🐶 $breed",
                fontSize = 17.sp,
                color = Color.Black,
                style = Typography.bodyLarge,
                modifier = Modifier.padding(top = 20.dp, start = 10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .clickable {
                            viewModel.sendAction(DetailContract.Action.DownloadDevice(image))
                        }
                        .background(
                            color = Yellow,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                ) {
                    Text(
                        text = "디바이스에 저장",
                        fontSize = 16.sp,
                        color = Color.White,
                        style = Typography.bodyLarge,
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            color = Red,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            viewModel.sendAction(DetailContract.Action.LikeImage(image))
                        }
                        .padding(10.dp)
                ) {
                    Text(
                        text = "좋아요 💖",
                        fontSize = 16.sp,
                        color = Color.White,
                        style = Typography.bodyLarge,
                    )
                }
            }
        }
    }
}