package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier
) {
    var image by remember{ mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .verticalScroll(rememberScrollState()), // remove .weight(1f) to add scrollbar
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(modifier = Modifier)
        Spacer(modifier = Modifier.height(40.dp))
        ArtImagePane(
            image = when(image) {
                0 -> R.drawable.mona_lisa
                1 -> R.drawable.the_scream
                2 -> R.drawable.the_starry_night
                3 -> R.drawable.guernica
                else -> R.drawable.mona_lisa
            },
            contentDescription = when(image) {
                0 -> R.string.monalisa
                1 -> R.string.the_scream
                2 -> R.string.the_starry_night
                3 -> R.string.guernica
                else -> R.string.monalisa
            },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.height(40.dp))
        ArtDescription(
            title = when(image) {
                0 -> R.string.monalisa
                1 -> R.string.the_scream
                2 -> R.string.the_starry_night
                3 -> R.string.guernica
                else -> R.string.monalisa
            },
            subtitle = when(image) {
                0 -> R.string.leonardo_da_vinci
                1 -> R.string.john_davies
                2 -> R.string.vincent_van_gogh
                3 -> R.string.pablo_picasso
                else -> R.string.leonardo_da_vinci
            },
            year = when(image) {
                0 -> R.string.monalisa_year
                1 -> R.string.the_scream_year
                2 -> R.string.the_starry_night_year
                3 -> R.string.guernica_year
                else -> R.string.monalisa_year
            },
        )
        Spacer(modifier = Modifier.height(20.dp))
        ButtonsBar(
            onClickPrevious = { image = (image - 1 + 4) % 4 },
            onClickNext = { image = (image + 1) % 4 },
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun ArtImagePane(
    @DrawableRes image: Int,
    @StringRes contentDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun ArtDescription(
    @StringRes title: Int,
    @StringRes subtitle: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 24.dp)
            .background(color = Color(0xffeee1ff)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = title),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = stringResource(id = subtitle),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "(" + stringResource(id = year) + ")",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ButtonsBar(
    modifier: Modifier = Modifier,
    onClickPrevious: () -> Unit = {},
    onClickNext: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(78.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onClickPrevious,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.previous),
                fontSize = 16.sp
            )
        }
        Button(
            onClick = onClickNext,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.next),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp(modifier = Modifier)
    }
}