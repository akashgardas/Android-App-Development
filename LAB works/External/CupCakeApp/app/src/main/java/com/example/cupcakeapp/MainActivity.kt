package com.example.cupcakeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeApp() {
    var quantity by remember { mutableStateOf(1) }
    var flavor by remember { mutableStateOf("Vanilla") }
    var date by remember { mutableStateOf("Today") }

    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cupcake Order App") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Order Your Cupcakes 🍰", fontSize = 22.sp)
            Spacer(Modifier.height(20.dp))

            // Quantity
            Text("Select Quantity:")
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                listOf(1, 6, 12).forEach {
                    Button(onClick = { quantity = it }) {
                        Text("$it")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Flavor
            Text("Select Flavor:")
            var expanded by remember { mutableStateOf(false) }
            Box {
                Button(onClick = { expanded = true }) {
                    Text(flavor)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Vanilla", "Chocolate", "Red Velvet", "Lemon").forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                flavor = it
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Date
            Text("Pick up Date:")
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                listOf("Today", "Tomorrow", "Day After").forEach {
                    Button(onClick = { date = it }) {
                        Text(it)
                    }
                }
            }

            Spacer(Modifier.height(30.dp))

            // Summary
            Text(
                text = "Order Summary:\n$quantity x $flavor cupcakes\nPickup: $date",
                fontSize = 16.sp
            )

            Spacer(Modifier.height(30.dp))

            // Share Order
            Button(onClick = {
                val message =
                    "Cupcake Order:\nQuantity: $quantity\nFlavor: $flavor\nPickup Date: $date"
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, message)
                }
                context.startActivity(Intent.createChooser(intent, "Share Order via"))
            }) {
                Text("Send Order")
            }

            Spacer(Modifier.height(10.dp))

            // Cancel Order
            OutlinedButton(onClick = {
                quantity = 1
                flavor = "Vanilla"
                date = "Today"
            }) {
                Text("Cancel Order")
            }
        }
    }
}