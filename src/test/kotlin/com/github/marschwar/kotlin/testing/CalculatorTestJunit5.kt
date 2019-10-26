package com.github.marschwar.kotlin.testing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTestJunit5 {

    private val subject = Calculator()

    @Test
    fun `correctly adds two numbers`() {
        val result = subject.add(1, 2)

        assertEquals(3, result)
    }

    @Test
    fun `correctly adds multiple numbers`() {
        val result = subject.add(1, 2, 3, 4)

        assertEquals(10, result)
    }

}
