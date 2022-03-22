package com.example.migration

import com.example.migration.dish.Dish

class Sample {
    var dish: Dish? = null
}

fun main(args: Array<String>) {
    val sample = Sample()
    sample.dish = Dish().apply {
        price = 1.toBigInteger()
        name = "keba"
    }
    println("Heio sample dish name: ${sample.dish?.name}")
}