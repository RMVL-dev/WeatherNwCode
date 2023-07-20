package com.example.weather.ui.screens.test

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.weather.ui.theme.startGradient
import com.example.weather.ui.theme.endGradient


@Composable
fun Test(
    modifier: Modifier = Modifier
){
    Canvas(
        modifier = modifier.fillMaxSize(),
        onDraw = {
            val chartWidth = size.width
            val chartHeight = size.height
            val brush = Brush.horizontalGradient(
                listOf(
                    startGradient,
                    endGradient
                )
            )
            val path = AndroidPath()
            path.moveTo(-100f, 20f)
            path.cubicTo(
                -100f,1250f,
                chartWidth+100.dp.value,20f,
                chartWidth+100.dp.value, 1250f
            )
            drawPath(
                path = path,
                brush = brush,
                style = Stroke(30.dp.value)
            )

        }
    )
}


@Preview(showBackground = true)
@Composable
fun TestPreview(){
    Test()
}