package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard(
                        name = stringResource(R.string.full_name),
                        title = stringResource(R.string.title)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(name: String, title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xffb1e0b4))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TitleInformation(name, title, modifier.weight(1f))
        ContactInformation()
    }
}

@Composable
fun TitleInformation(name: String, title:String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.android_logo)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = name,
            fontSize = 36.sp
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color(0xff064f08)
        )
    }
}

@Composable
fun ContactInformation(modifier: Modifier = Modifier) {
    val myAppIcons = Icons.Rounded
    Column(
        modifier = Modifier.padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = myAppIcons.Phone,
                contentDescription = null,
                tint = Color(0xff064f08)
            )
            Text(
                text = stringResource(R.string.telephone),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = myAppIcons.Share,
                contentDescription = null,
                tint = Color(0xff064f08)
            )
            Text(
                text = stringResource(R.string.social_media),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = myAppIcons.Email,
                contentDescription = null,
                tint = Color(0xff064f08)
            )
            Text(
                text = stringResource(R.string.email),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(
            name = stringResource(R.string.full_name),
            title = stringResource(R.string.title)
        )
    }
}