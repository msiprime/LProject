package com.msicoding.lproject.presention.profile

import WeatherScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.msicoding.lproject.presention.di.WeatherRepository
import com.msicoding.lproject.presention.di.WeatherResponse
import com.msicoding.lproject.presention.sign_in.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var weatherInformation by remember { mutableStateOf<WeatherResponse?>(null) }
    var location by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl, contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (userData?.userName != null) {
            Text(
                text = userData.userName,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = onSignOut) {
            Text(text = "Sign Out")
        }
        WeatherScreen(
            onLocationEntered = { enteredLocation ->
                location = enteredLocation
            },
            onWeatherInfoReceived = { weatherInfo ->
                coroutineScope.launch {
                    try {
                        val result = withContext(Dispatchers.IO) {
                            WeatherRepository.getWeatherForLocation(weatherInfo)
                        }
                        weatherInformation = result
                        println("Weather information received: Temperature is ${result.main.temp} °C in ${result.name}")
                    } catch (e: Exception) {
                        println("Error fetching weather: ${e.message}")
                    }
                }
            }
        )

        weatherInformation?.let {
            Text("Weather: ${it.main.temp} °C in ${it.name}")
        }
    }
}

