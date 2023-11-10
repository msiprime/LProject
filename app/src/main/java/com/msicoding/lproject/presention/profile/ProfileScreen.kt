package com.msicoding.lproject.presention.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.msicoding.lproject.core.di.GetWeatherUseCase
import com.msicoding.lproject.core.di.NetworkModule
import com.msicoding.lproject.core.di.WeatherRepositoryImpl
import com.msicoding.lproject.data.model.WeatherResponse
import com.msicoding.lproject.presention.sign_in.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit
) {
    var location by remember { mutableStateOf("") }
    val weatherInformation = remember { mutableStateOf<WeatherResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val getWeatherUseCase =
        GetWeatherUseCase(WeatherRepositoryImpl(NetworkModule.provideWeatherService(NetworkModule.provideRetrofit())))


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        userData?.profilePictureUrl?.let { profilePictureUrl ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            ) {
                AsyncImage(
                    model = profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        userData?.userName?.let { userName ->
            Text(
                text = userName,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "Enter Location",
            color = Color.Black,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        val locationState = remember { mutableStateOf("") }
        OutlinedTextField(
            value = locationState.value,
            onValueChange = { locationState.value = it },
            label = { Text("Location") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                location = locationState.value
                fetchWeather(
                    getWeatherUseCase,
                    locationState.value,
                    weatherInformation,
                    coroutineScope
                )
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            location = locationState.value
            fetchWeather(getWeatherUseCase, locationState.value, weatherInformation, coroutineScope)
        }) {
            Text("Get Weather")
        }

        weatherInformation.value?.let { weather ->
            Text("Weather: ${weather.main.temp} °C in ${weather.name}")
        }

        Button(onClick = onSignOut) {
            Text("Sign Out")
        }
    }
}

private fun fetchWeather(
    getWeatherUseCase: GetWeatherUseCase,
    location: String,
    weatherInformation: MutableState<WeatherResponse?>,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        try {
            val result = getWeatherUseCase(location)
            weatherInformation.value = result
            println("Weather information received: Temperature is ${result.main.temp} °C in ${result.name}")
        } catch (e: Exception) {
            println("Error fetching weather: ${e.message}")
        }
    }
}
