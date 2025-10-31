package com.example.ordercupcakes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PickUpScreen(
    onNext: () -> Unit,
    onCancel: () -> Unit,
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier
) {
    val options = viewModel.getPickupOptions()
    Column(
        modifier = modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select Pickup Date")
        Spacer(modifier = Modifier.height(16.dp))
        options.forEach { option ->
            Button(
                onClick = {
                    viewModel.setDate(option)
                    onNext()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = option)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = onCancel
        ) {
            Text(text = "Cancel")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PickUpScreenPreview() {
    PickUpScreen(
        onNext = {},
        onCancel = {},
        viewModel = OrderViewModel()
    )
}
