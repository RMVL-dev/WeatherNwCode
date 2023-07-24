package com.example.weather.ui.screens.test

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.AndroidPath
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.Forecast
import com.example.weather.data.Weather
import com.example.weather.ui.screens.main.ForecastList
import com.example.weather.ui.screens.main.TestMainWeather
import com.example.weather.ui.theme.endGradient
import com.example.weather.ui.theme.middleGradient
import com.example.weather.ui.theme.startGradient

@Composable
fun TestMainScreen(
    modifier: Modifier = Modifier,
    weather: Weather,
    forecast: Forecast,
    navigateToDetails:()->Unit,
    navigateToForecast: ()->Unit
){
    val path = AndroidPath()
    val mainImageBitmap = ImageBitmap
        .imageResource(id = R.drawable.background_image)
        .asAndroidBitmap()
    val mainImage = Bitmap.createScaledBitmap(
        mainImageBitmap,
        1300,
        1500,
        false
    ).asImageBitmap()
    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier
                .fillMaxSize()
                .drawWithCache {
                    onDrawBehind {
                        val chartWidth = size.width
                        drawImage(
                            image = mainImage,
                            srcOffset = IntOffset(100, 0),
                            srcSize = IntSize(chartWidth.toInt(), 2000)
                        )
                    }
                }
            )
            TestMainWeather(weather = weather) {
                navigateToForecast()
            }
        }
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .drawWithCache {
                        val chartWidth = size.width
                        val chartHeight = size.height
                        path.moveTo(-50f, 20f)
                        path.cubicTo(
                            -100f, chartHeight - 300f,
                            chartWidth + 200.dp.value, 0f,
                            chartWidth + 50.dp.value, chartHeight - 300f
                        )
                        val pathBelowLine = AndroidPath()
                        pathBelowLine.addPath(path = path)
                        pathBelowLine.lineTo(chartWidth, chartHeight)
                        pathBelowLine.lineTo(0f, chartHeight)
                        pathBelowLine.lineTo(0f, 20f)
                        onDrawBehind {
                            drawPath(
                                path = pathBelowLine,
                                brush = Brush
                                    .verticalGradient(
                                        colorStops =
                                        arrayOf(
                                            0.0f to endGradient,
                                            0.7f to middleGradient,
                                            1.0f to startGradient
                                        )
                                    )
                            )
                        }
                    }
            )
            ForecastList(
                modifier = Modifier.padding(bottom = 10.dp),
                forecast = forecast
            )
        }
        Spacer(
            modifier = modifier
                .fillMaxSize()
                .drawWithCache {
                    onDrawBehind {
                        val chartWidth = size.width
                        val chartHeight = size.height
                        path.moveTo(-50f, 20f)
                        path.cubicTo(
                            -100f, chartHeight - 300f,
                            chartWidth + 200.dp.value, 0f,
                            chartWidth + 50.dp.value, chartHeight - 300f
                        )
                        drawPath(
                            path = path,
                            brush = Brush
                                .horizontalGradient(
                                    colorStops =
                                    arrayOf(
                                        0.0f to startGradient,
                                        0.2f to middleGradient,
                                        1.0f to endGradient
                                    ),
                                ),
                            style = Stroke(13.dp.toPx())
                        )
                    }
                }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTestMainScreen(){
//    TestMainScreen()
//}
