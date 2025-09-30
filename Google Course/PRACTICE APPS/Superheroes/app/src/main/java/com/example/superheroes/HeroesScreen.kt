package com.example.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme


@Composable
fun SuperheroesScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_small)
        ),
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        items(HeroesRepository.heroes) {
            SuperheroCard(
                heroName = it.nameRes,
                heroDescription = it.descriptionRes,
                heroImage = it.imageRes,
            )
        }
    }
}

@Composable
fun SuperheroCard(
    @StringRes heroName: Int,
    @StringRes heroDescription: Int,
    @DrawableRes heroImage: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .height(72.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(heroName),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(heroDescription),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(16.dp))
            HeroIcon(heroImage)
        }
    }
}

@Composable
fun HeroIcon(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
                .clip(MaterialTheme.shapes.small)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SuperHeroCardPreview() {
//    SuperheroesTheme {
//        SuperHeroCard(
//            heroName = R.string.hero1,
//            heroDescription = R.string.description1,
//            heroImage = R.drawable.android_superhero1
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun SuperheroesAppPreview() {
    SuperheroesTheme {
        SuperheroesScreen()
    }
}