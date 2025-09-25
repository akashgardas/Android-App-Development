package com.example.androidtrivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidtrivia.ui.theme.AndroidTriviaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTriviaTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "title"
                ) {
                    composable("title") {
                        TitleScreen(navController)
                    }
                    composable("game") {
                        GameScreen()
                    }
                }
            }
        }
    }
}

data class Question(
    val text: String,     
    val options: List<String>,
    val answer: String
)

@Composable
fun TitleScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Welcome All",
            color = Color.Red,
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(45.dp))
        Button(
            onClick = { navController.navigate("game") }
        ) {
            Text(
                text = "Start Game"
            )
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    val question = Question(
        text = "What is the official language for Android Development?",
        options = listOf("Java", "Kotlin", "Python", "C++"),
        answer = "Kotlin"
    )

    var selectedOption by remember{ mutableStateOf("") }
    var showResult by remember{ mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question.text,
            fontSize = 20.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(26.dp))

        // Options
        question.options.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == it,
                    onClick = {
                        selectedOption = it
                        showResult = false
                    }
                )
                Text(
                    text = it,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        // Submit Buton
        Button(
            onClick = { showResult = true },
            enabled = selectedOption != ""
        ) {
            Text(
                text = "Submit"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Result
        if(showResult) {
            if(selectedOption == question.answer) {
                Text(
                    text = "Correct",
                    color = Color(0xff007d21),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            } else {
                Text(
                    text = "Wrong Answer",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Correct answer is ${question.answer}.",
                    color = Color(0xff007d21),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AndroidTriviaPreview() {
    AndroidTriviaTheme {
        GameScreen()
    }
}