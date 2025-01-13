package com.example.weatherapp.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeatherListScreen(
    weatherData: WeatherModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val exoFont = FontFamily(
            Font(R.font.exofont, FontWeight.Normal), Font(R.font.exofontbold, FontWeight.Bold)
        )

        val formattedDate = formatLocalTime(weatherData.location.localtime)


        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentDescription = "Stars",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weatherData.location.name, fontSize = 36.sp, fontFamily = exoFont
            )
            Text(
                text = formattedDate,
                color = Color.White.copy(alpha = 0.5f),
                fontFamily = exoFont,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${weatherData.current.temp_c}°",
                    color = Color.White,
                    fontFamily = exoFont,
                    fontSize = 150.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = weatherData.current.condition.text,
                        color = Color.White,
                        fontFamily = exoFont,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    AsyncImage(
                        model = "https:${weatherData.current.condition.icon}",
                        contentDescription = "",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            Spacer(Modifier.height(24.dp))

            Surface(
                modifier = Modifier.height(150.dp)
                    .width(330.dp),
                shape = RoundedCornerShape(40.dp),
                color = Color.White.copy(alpha = 0.2f)
            ) {
                Row(
                    modifier = Modifier.padding(30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            Icons.Default.Waves,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "${weatherData.current.wind_kph}km/h",
                            color = Color.White,
                            fontFamily = exoFont,
                            fontSize = 18.sp
                        )
                        Text(
                            "Wind",
                            color = Color.White.copy(alpha = 0.7f),
                            fontFamily = exoFont,
                            fontSize = 16.sp
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            Icons.Outlined.WaterDrop,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "${weatherData.current.humidity}%",
                            color = Color.White,
                            fontFamily = exoFont,
                            fontSize = 18.sp
                        )
                        Text(
                            "Humidity",
                            color = Color.White.copy(alpha = 0.7f),
                            fontFamily = exoFont,
                            fontSize = 16.sp
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            Icons.Outlined.Visibility,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "${weatherData.current.vis_km}km",
                            color = Color.White,
                            fontFamily = exoFont,
                            fontSize = 18.sp
                        )
                        Text(
                            "Visibility",
                            color = Color.White.copy(alpha = 0.7f),
                            fontFamily = exoFont,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 55.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Forecast",
                    color = Color(0xff014421).copy(0.7f),
                    fontFamily = exoFont,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )


            }

        }
    }
}

fun formatLocalTime(localtime: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(localtime, inputFormatter)

    val day = dateTime.dayOfMonth
    val month = dateTime.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val dayOfWeek = dateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

    return "$day $month, $dayOfWeek"
}