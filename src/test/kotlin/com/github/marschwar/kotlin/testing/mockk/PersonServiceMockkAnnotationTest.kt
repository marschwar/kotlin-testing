package com.github.marschwar.kotlin.testing.mockk

import com.github.marschwar.kotlin.testing.PersonDao
import com.github.marschwar.kotlin.testing.PersonService
import io.kotlintest.matchers.boolean.shouldBeFalse
import io.kotlintest.matchers.boolean.shouldBeTrue
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class PersonServiceMockkAnnotationTest {

    @InjectMockKs
    lateinit var subject: PersonService

    @RelaxedMockK
    lateinit var dao: PersonDao

    @Test
    fun `add person if it does not already exist`() {
        every { dao.exists(any()) } returns false

        val name = "Joe"
        val result = subject.add(name)

        result.shouldBeTrue()

        verifyAll {
            dao.exists(name)
            dao.save(name)
        }
    }

    @Test
    fun `does not add person if it already exists`() {
        every { dao.exists(any()) } returns true

        val name = "Joe"
        val result = subject.add(name)

        result.shouldBeFalse()

        verify { dao.exists(name) }
        verify(inverse = true) { dao.save(name) }
    }
}
