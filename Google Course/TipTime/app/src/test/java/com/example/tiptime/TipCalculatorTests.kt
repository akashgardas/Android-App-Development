package com.example.tiptime

import org.junit.Test

import org.junit.Assert.*
import java.text.NumberFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNotRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_20PercentRoundUp() {
        val amount = 4.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(1)
        val actualTip = calculateTip(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip)
    }
}