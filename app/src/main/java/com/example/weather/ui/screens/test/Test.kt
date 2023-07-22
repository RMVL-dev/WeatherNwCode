package com.example.weather.ui.screens.test

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.example.weather.R
import com.example.weather.ui.theme.startGradient
import com.example.weather.ui.theme.middleGradient
import com.example.weather.ui.theme.endGradient


@Composable
fun Test(
    modifier: Modifier = Modifier
){
    val imageBitmap = ImageBitmap
        .imageResource(R.drawable.second_fon)

    val background = Bitmap.createScaledBitmap(
        imageBitmap.asAndroidBitmap(),
        1300,
        1500,
        false
    )
    Canvas(
        modifier = modifier.fillMaxSize(),
        onDraw = {
            val chartWidth = size.width
            val chartHeight = size.height
            val brush = Brush.horizontalGradient(
                colorStops = arrayOf(
                    0.0f to startGradient,
                    0.3f to middleGradient,
                    1.0f to endGradient
                )
            )
            val brushBelowLine = Brush.verticalGradient(
                colorStops = arrayOf(
                    0.0f to endGradient,
                    0.7f to middleGradient,
                    1.0f to startGradient
                )
            )
            val path = AndroidPath()
            path.moveTo(-50f, 20f)
            path.cubicTo(
                -100f,chartHeight-300f,
                chartWidth+200.dp.value,0f,
                chartWidth+50.dp.value, chartHeight-300f
            )
            val pathBelowLine = AndroidPath()
            pathBelowLine.addPath(path = path)
            pathBelowLine.lineTo(chartWidth, chartHeight)
            pathBelowLine.lineTo(0f,chartHeight)
            pathBelowLine.lineTo(0f, 20f)

            val pathForImage = AndroidPath()
            pathForImage.addPath(path = path)
            pathForImage.lineTo(chartWidth,0f)
            pathForImage.lineTo(0f,0f)
            pathForImage.lineTo(-50f, 20f)

            drawImage(
                image = background.asImageBitmap(),
                srcOffset = IntOffset(150,0),
                srcSize = IntSize(chartWidth.toInt(),2000)
            )

            drawPath(
                path = pathBelowLine,
                brush = brushBelowLine,
                style = Fill
            )
            drawPath(
                path = path,
                brush = brush,
                style = Stroke(
                    40.dp.value,
                )
            )

        }
    )
    
}


@Preview(showBackground = true)
@Composable
fun TestPreview(){
    Test()
}