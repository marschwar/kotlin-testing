package com.github.marschwar.kotlin.testing.kotlintest

import com.github.marschwar.kotlin.testing.Calculator
import io.kotlintest.shouldBe
import io.kotlintest.specs.FeatureSpec

class CalculatorFeatureSpec : FeatureSpec({
    val subject = Calculator()

    feature("Adding numbers") {

        scenario("adds two numbers") {
            subject.add(1, 2) shouldBe 3
        }

        scenario("adds multiple numbers") {
            subject.add(1, 2, 3, 4) shouldBe 10
        }
    }
})
