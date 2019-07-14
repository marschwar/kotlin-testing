package com.github.marschwar.kotlin.testing

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object CalculatorFeature : Spek({
    Feature("Calculator") {
        val calculator by memoized { Calculator() }

        Scenario("Adding numbers") {
            lateinit var result: Number

            When("1 and 2 are added") {
                result = calculator.add(1, 2)
            }

            Then("the result is 3") {
                assertThat(result).isEqualTo(3)
            }
        }
    }
})