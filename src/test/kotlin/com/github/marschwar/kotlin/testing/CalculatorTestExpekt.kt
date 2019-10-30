package com.github.marschwar.kotlin.testing

import com.winterbe.expekt.expect
import com.winterbe.expekt.should
import org.junit.jupiter.api.Test

class CalculatorTestExpekt {

    private val subject = Calculator()

    @Test
    fun `correctly adds two numbers`() {
        val result = subject.add(1, 2)

        result.should.equal(3)
    }

    @Test
    fun `correctly adds multiple numbers`() {
        val result = subject.add(1, 2, 3, 4)

        expect(result).to.equal(10)
    }
}
