package com.example.a30daysofsewingtips.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val day: Int,
    @DrawableRes val image: Int,
    @StringRes val tip: Int
)
