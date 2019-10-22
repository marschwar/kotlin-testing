package com.github.marschwar.kotlin.testing

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorTest {

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
