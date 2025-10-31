package com.example.ordercupcakes

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen(
    viewModel: OrderViewModel,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val summary = viewModel.getOrderSummary()
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = summary)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { shareOrder(context, summary) }
        ) {
            Text("Share Order")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = onCancelButtonClicked
        ) {
            Text("Cancel")
        }
    }
}

fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(Intent.createChooser(intent, "Share Order"))
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SummaryScreenPreview() {
    SummaryScreen(
        viewModel = OrderViewModel(),
        onCancelButtonClicked = {}
    )
}
