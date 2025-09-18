package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var image by remember { mutableIntStateOf(0) }
    var squeezeCount by remember { mutableIntStateOf((2..4).random()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xfffcd00d))
                .padding(top = 36.dp, bottom = 12.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    when (image) {
                        0 -> R.drawable.lemon_tree
                        1 -> R.drawable.lemon_squeeze
                        2 -> R.drawable.lemon_drink
                        else -> R.drawable.lemon_restart
                    }
                ),
                contentDescription = stringResource(
                    when (image) {
                        0 -> R.string.lemon_tree_text
                        1 -> R.string.lemon_text
                        2 -> R.string.glass_of_lemonade_text
                        else -> R.string.empty_glass_text
                    }
                ),
                modifier = Modifier
                    .clickable {
                        if (image == 1) {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                squeezeCount = (2..4).random()
                                image = (image + 1) % 4
                            }
                        } else
                            image = (image + 1) % 4
                    }
                    .size(200.dp)
                    .background(
                        color = Color(0xffa9d4b4),
                        shape = RoundedCornerShape(20.dp)
                    )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(
                    when (image) {
                        0 -> R.string.lemon_tree_text
                        1 -> R.string.lemon_text
                        2 -> R.string.glass_of_lemonade_text
                        else -> R.string.empty_glass_text
                    }
                ),
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp(modifier = Modifier)
    }
}