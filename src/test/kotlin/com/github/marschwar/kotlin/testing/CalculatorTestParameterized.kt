package com.github.marschwar.kotlin.testing

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.tables.row
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CalculatorTestParameterized {

    private val subject = Calculator()

    @Test
    fun `correctly adds two numbers`() {
        forall(
            row(1, 2, 0, 0, 3),
            row(1, 2, 3, 4, 10)
        )
        { addend1, addend2, addend3, addend4, sum ->
            subject.add(addend1, addend2, addend3, addend4) shouldBe sum
        }
    }

    @ParameterizedTest
    @CsvSource(
        "1, 2, 0, 0, 3",
        "1, 2, 3, 4, 10"
    )
    fun `correctly adds multiple numbers`(addend1: Int, addend2: Int, addend3: Int, addend4: Int, sum: Int) {
        subject.add(addend1, addend2, addend3, addend4) shouldBe sum
    }
}
