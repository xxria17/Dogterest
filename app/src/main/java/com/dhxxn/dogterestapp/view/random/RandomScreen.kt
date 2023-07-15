package com.dhxxn.dogterestapp.view.random

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.checkSelfPermission
import coil.compose.AsyncImage
import com.dhxxn.dogterestapp.R
import com.dhxxn.dogterestapp.ui.theme.Typography
import com.dhxxn.dogterestapp.view.base.BaseScreen
import java.text.SimpleDateFormat
import java.util.Date

class RandomScreen(
    private val viewModel: RandomViewModel,
    private val activity: Activity
) : BaseScreen() {

    @Composable
    override fun CreateContent() {
        val context = LocalContext.current
        var isShowDialog by remember { mutableStateOf(false)}

        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { _isGranted ->
            if (_isGranted) {
                isShowDialog = true
            } else {
                Toast.makeText(context, "권한이 없습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                context.startActivity(intent)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "오늘의 개",
                    fontSize = 35.sp,
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 40.dp),
                    color = Color.Black
                )

                AsyncImage(
                    model = viewModel.state.randomImg.value(),
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.picture),
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    longPressHaptic(context)
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                        isShowDialog = true
                                    } else {
                                        if (checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                            isShowDialog = true
                                        } else {
                                            launcherMultiplePermissions.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        }
                                    }
                                }
                            )
                        },
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = Color(0xFFB3E7FF)
                        )
                        .padding(10.dp)
                        .clickable {
                            clickHaptic(context)
                            viewModel.sendAction(RandomContract.RandomAction.RequestNewImage)
                        }
                ) {
                    Text(
                        text = "다른 개 보기",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }

        if (isShowDialog) {
            AlertDialog(
                onDismissRequest = { isShowDialog = false },
                title = {
                    Text(text = "저장하시겠습니까?")
                },
                text = {
                    Text(text = "해당 사진이 저장됩니다.")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            downloadImage(viewModel.state.randomImg.value(), context)
                            isShowDialog = false
                        }
                    ) {
                        Text("저장")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {    isShowDialog = false}
                    ) {
                        Text("취소")
                    }
                }
            )
        }
    }

    private fun downloadImage(url: String, context: Context) {
        val fileName = "/Dogterest/${SimpleDateFormat("yyyyMMddHHmmss").format(Date())}.jpg"

        val req = DownloadManager.Request(Uri.parse(url))
        req.setTitle(fileName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setMimeType("image/*")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                fileName
            )

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        manager.enqueue(req)

        Toast.makeText(context, "저장되었습니다!", Toast.LENGTH_SHORT).show()
    }

    private fun longPressHaptic(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val vibration = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            vibrator.vibrate(vibration)
        } else {
            vibrator.vibrate(100L)
        }
    }

    private fun clickHaptic(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val vibration = VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
            vibrator.vibrate(vibration)
        } else {
            vibrator.vibrate(100L)
        }
    }
}