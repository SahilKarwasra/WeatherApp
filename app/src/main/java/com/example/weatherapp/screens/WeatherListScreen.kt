package com.example.weatherapp.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Forecastday
import com.example.weatherapp.data.models.WeatherModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeatherListScreen(weatherData: WeatherModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val exoFont = FontFamily(
            Font(R.font.exofont, FontWeight.Normal), Font(R.font.exofontbold, FontWeight.Bold)
        )

        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherHeader(weatherData, exoFont)
            Spacer(modifier = Modifier.height(32.dp))
            TemperatureSection(weatherData, exoFont)
            Spacer(modifier = Modifier.height(24.dp))
            WeatherDetailsSection(weatherData, exoFont)
            Spacer(modifier = Modifier.height(32.dp))
            ForecastSection(weatherData, exoFont)
        }
    }
}

@Composable
fun WeatherHeader(weatherData: WeatherModel, fontFamily: FontFamily) {
    val formattedDate = formatLocalTime(weatherData.location.localtime)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = weatherData.location.name,
            fontSize = 36.sp,
            fontFamily = fontFamily
        )
        Text(
            text = formattedDate,
            color = Color.White.copy(alpha = 0.5f),
            fontFamily = fontFamily,
            fontSize = 16.sp
        )
    }
}

@Composable
fun TemperatureSection(weatherData: WeatherModel, fontFamily: FontFamily) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${weatherData.current.temp_c}°",
            color = Color.White,
            fontFamily = fontFamily,
            fontSize = 150.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = weatherData.current.condition.text,
                color = Color.White,
                fontFamily = fontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            AsyncImage(
                model = "https:${weatherData.current.condition.icon}",
                contentDescription = "Weather Icon",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
fun WeatherDetailsSection(weatherData: WeatherModel, fontFamily: FontFamily) {
    Surface(
        modifier = Modifier
            .height(150.dp)
            .width(330.dp),
        shape = RoundedCornerShape(40.dp),
        color = Color.White.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WeatherDetailItem(
                icon = Icons.Default.Waves,
                value = "${weatherData.current.wind_kph}km/h",
                label = "Wind",
                fontFamily = fontFamily
            )
            WeatherDetailItem(
                icon = Icons.Outlined.WaterDrop,
                value = "${weatherData.current.humidity}%",
                label = "Humidity",
                fontFamily = fontFamily
            )
            WeatherDetailItem(
                icon = Icons.Outlined.Visibility,
                value = "${weatherData.current.vis_km}km",
                label = "Visibility",
                fontFamily = fontFamily
            )
        }
    }
}

@Composable
fun WeatherDetailItem(icon: ImageVector, value: String, label: String, fontFamily: FontFamily) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(icon, contentDescription = "", tint = Color.White, modifier = Modifier.size(40.dp))
        Text(
            text = value,
            color = Color.White,
            fontFamily = fontFamily,
            fontSize = 18.sp
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontFamily = fontFamily,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ForecastSection(weatherData: WeatherModel, fontFamily: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Forecast",
            color = Color(0xff014421).copy(0.7f),
            fontFamily = fontFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(weatherData.forecast.forecastday) { forecastDay ->
                ForecastItem(forecastDay, fontFamily)
            }
        }
    }
}

@Composable
fun ForecastItem(forecastDay: Forecastday, fontFamily: FontFamily) {
    Surface(
        modifier = Modifier
            .height(220.dp)
            .width(70.dp),
        shape = RoundedCornerShape(28.dp),
        color = Color.LightGray.copy(alpha = 0.1f),
        border = BorderStroke(width = 2.dp, color = Color.White.copy(0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = getDayOfWeek(forecastDay.date),
                color = Color.White,
                fontFamily = fontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = "https:${forecastDay.day.condition.icon}",
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = "${forecastDay.day.maxtemp_c}°",
                color = Color.White,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${forecastDay.day.mintemp_c}°",
                color = Color.White.copy(alpha = 0.8f),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



fun getDayOfWeek(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateString, formatter)
    return date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
}

fun formatLocalTime(localtime: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(localtime, inputFormatter)

    val day = dateTime.dayOfMonth
    val month = dateTime.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val dayOfWeek = dateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

    return "$day $month, $dayOfWeek"
}
