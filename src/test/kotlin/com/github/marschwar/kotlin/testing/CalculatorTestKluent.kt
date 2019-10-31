package com.github.marschwar.kotlin.testing

import org.amshove.kluent.`should equal`
import org.amshove.kluent.shouldEqual
import kotlin.test.Test

class CalculatorTestKluent {

    private val subject = Calculator()

    @Test
    fun `correctly adds two numbers`() {
        val result = subject.add(1, 2)

        result.shouldEqual(3)
    }

    @Test
    fun `correctly adds multiple numbers`() {
        val result = subject.add(1, 2, 3, 4)

        result `should equal` 10
    }
}
