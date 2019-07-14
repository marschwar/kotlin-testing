package com.github.marschwar.kotlin.testing

class Calculator {
    fun add(vararg numbers: Int): Int {
        require(numbers.size >= 1) { "Please provide at least two numbers" }
        return numbers.sum()
    }
}
