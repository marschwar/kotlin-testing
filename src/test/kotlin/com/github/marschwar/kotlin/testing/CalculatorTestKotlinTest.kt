package com.github.marschwar.kotlin.testing

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class CalculatorTestKotlinTest {

    private val subject = Calculator()

    @Test
    fun `correctly adds two numbers`() {
        val result = subject.add(1, 2)

        result.shouldBe(3)
    }

    @Test
    fun `correctly adds multiple numbers`() {
        val result = subject.add(1, 2, 3, 4)

        result.shouldBe(10)
    }

}
