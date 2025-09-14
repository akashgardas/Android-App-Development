package com.example.headsortails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            flipCoin()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun flipCoin() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            //        var coinValue = (1..2).random()
            var coinValue by remember { mutableIntStateOf(1) }
            var coinText: String
            if (coinValue == 1)
                coinText = "Heads"
            else
                coinText = "Tails"

            var context = LocalContext.current;

            Column {
                Image(
                    painter = painterResource(id = getCoinImage(coinValue)),
                    contentDescription = "Coin Image",
                    modifier = Modifier.size(150.dp)
                )
                Text(text = coinText)
                Spacer(modifier = Modifier.size(16.dp))

                Button(onClick = {
                    coinValue = (1..2).random()
                }) {
                    Text(text = "Flip Coin")
                }

//                Toast.makeText(context, "Coin Flipped", Toast.LENGTH_SHORT).show()
            }
        }


}

fun getCoinImage(coinValue: Int) : Int {
    return when(coinValue) {
        1 -> R.drawable.heads
        else -> R.drawable.tails
    }
}