package com.github.marschwar.kotlin.testing


import org.amshove.kluent.`should be greater than`
import org.amshove.kluent.`should be in range`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.shouldBeInRange
import org.amshove.kluent.shouldBePositive
import org.amshove.kluent.shouldEqual
import kotlin.test.Test

class CalculatorTestKluent {

    private val subject = Calculator()

    @Test
    fun `correctly adds two numbers`() {
        val result = subject.add(1, 2)

        result.shouldBeInRange(2, 4)
            .shouldEqual(3)
    }

    @Test
    fun `correctly adds multiple numbers`() {
        val result = subject.add(1, 2, 3, 4)

        result `should be in range` 5..15 and result `should equal` 10
    }
}
