package com.example.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DisplayActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val username = intent.getStringExtra("username")
            DisplayUsername(username)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayUsername(username: String?="akash") {
    Column(
        modifier= Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Hello $username", fontSize = 32.sp, color = Color.Red)
    }
}