package com.github.marschwar.kotlin.testing.kotlintest

import com.github.marschwar.kotlin.testing.Calculator
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class CalculatorStringSpec : StringSpec({
    val subject = Calculator()

    "Two numbers are correctly added" {
        subject.add(1, 2) shouldBe 3
    }
    "Multiple numbers are correctly added" {
        subject.add(1, 2, 3, 4) shouldBe 10
    }
})
