package com.example.loginpage

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    var username by remember {mutableStateOf(value="")}
    var password by remember {mutableStateOf(value="")}
    var msg by remember {mutableStateOf(value="")}

    Column(
        modifier=Modifier.fillMaxSize().padding(all=32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        val context: Context = LocalContext.current
        Image(
            painter = painterResource(R.drawable.android_image),
            contentDescription = "Android Image"
        )

        // Username
        Text(text="User Name")
        TextField(
            value=username,
            onValueChange={username=it},
            placeholder={Text(text="Enter User Name")},
            modifier=Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(16.dp))

        // Password
        Text(text="Password")
        TextField(
            value=password,
            onValueChange={password=it},
            placeholder={Text(text="Enter Password")},
            visualTransformation= PasswordVisualTransformation(),
            modifier=Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(16.dp))

        // Button
        Button(
            onClick = {
                if (username == "admin" && password == "admin123") {
                    msg = "Login Successful"
//                    var urlintent = Intent(
//                        Intent.ACTION_VIEW,
//                        Uri.parse("https://github.com/akashgardas")
//                    )
                    val intent = Intent(context, DisplayActivity::class.java)
                    intent.putExtra("username", username)
                    context.startActivity(intent)

//                    context.startActivity(urlintent)
                    Toast.makeText(
                        context,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                } else  {
                    msg = "Login Failed"
                    Toast.makeText(
                        context,
                        "Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text="Login")
        }

        // After button
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text=msg,
            color=if(msg.contains("Success")) Color.Magenta else Color.Red
        )
    }
}
