package com.example.a30daysofsewingtips.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30daysofsewingtips.R
import com.example.a30daysofsewingtips.data.Datasource
import com.example.a30daysofsewingtips.model.Day
import com.example.a30daysofsewingtips.ui.theme._30DaysOfSewingTipsTheme

@Composable
fun SewingTipsScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SewingTipsTopAppBar() },
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
    ) { innerPadding ->
        val days = remember { Datasource().days }
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items(days) { day ->
                SewingTipsCard(day = day, modifier = modifier)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SewingTipsTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun SewingTipsCard(day: Day, modifier: Modifier = Modifier) {
    var clicked by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = { clicked = !clicked },
        modifier = modifier
            .clip(
                if (!clicked)
                    MaterialTheme.shapes.medium
                else
                    MaterialTheme.shapes.small
            )
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Box() {
                Image(
                    painter = painterResource(day.image),
                    contentDescription = stringResource(day.tip),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.image_size))
                        .clip(MaterialTheme.shapes.medium)
                )
                Text(
                    text = stringResource(day.day),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .align(Alignment.BottomStart)

                )
            }
            if (clicked) {
                Text(
                    text = stringResource(day.tip),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
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
fun SewingTipsScreenPreview() {
    _30DaysOfSewingTipsTheme {
        SewingTipsScreen()
    }
}
