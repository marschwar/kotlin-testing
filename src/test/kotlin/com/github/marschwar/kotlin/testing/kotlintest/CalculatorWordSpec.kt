package com.github.marschwar.kotlin.testing.kotlintest

import com.github.marschwar.kotlin.testing.Calculator
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class CalculatorWordSpec : WordSpec({
    val subject = Calculator()

    "Adding numbers" should {

        "add two numbers" {
            subject.add(1, 2) shouldBe 3
        }

        "add multiple numbers" {
            subject.add(1, 2, 3, 4) shouldBe 10
        }
    }
})
