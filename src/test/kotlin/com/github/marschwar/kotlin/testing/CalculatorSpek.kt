package com.github.marschwar.kotlin.testing

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

object CalculatorSpek : Spek({
    val subject by memoized(CachingMode.EACH_GROUP) { Calculator() }

    describe("adding numbers") {
        context("no numbers are provided") {
            it("throws an exception") {
                assertThatThrownBy { subject.add() }.isInstanceOf(IllegalArgumentException::class.java)
            }
        }

        describe("two numbers are provided") {
            lateinit var result: Number

            beforeGroup { result = subject.add(1, 2) }

            it("correctly adds given numbers") {
                assertThat(result).isEqualTo(3)
            }
        }

        describe("multiple numbers are provided") {
            lateinit var result: Number

            beforeGroup { result = subject.add(1, 2, 3, 4) }

            it("correctly adds given numbers") {
                assertThat(result).isEqualTo(10)
            }
        }
    }
})
