package com.msicoding.lproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen(onSearch: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
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
            keyboardActions = KeyboardActions(onSearch = { onSearch(locationState.value) })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onSearch(locationState.value) }) {
            Text("Get Weather")
        }
    }
}

@Composable
@Preview
fun PreviewWeatherScreen() {
    WeatherScreen(onSearch = {})
}
