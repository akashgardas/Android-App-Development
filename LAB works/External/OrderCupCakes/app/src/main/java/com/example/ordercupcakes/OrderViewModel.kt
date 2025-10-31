package com.example.ordercupcakes

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrderViewModel : ViewModel() {
    var quantity: Int = 0
        private set
    var flavor: String = ""
        private set
    var date: String = ""
        private set

    fun setQuantity(numberCupcakes: Int) {
        quantity = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        flavor = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        date = pickupDate
    }

    fun resetOrder() {
        quantity = 0
        flavor = ""
        date = ""
    }

    fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }

        return options
    }

    fun getOrderSummary(): String = "Order Summary:\nQuantity: $quantity\nFlavor: $flavor\nDate: $date"
}