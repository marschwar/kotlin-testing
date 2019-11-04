package com.github.marschwar.kotlin.testing

import com.nhaarman.mockitokotlin2.*
import io.kotlintest.matchers.boolean.shouldBeFalse
import io.kotlintest.matchers.boolean.shouldBeTrue
import org.junit.jupiter.api.Test

internal class PersonServiceMockitoKotlinTest {

    val dao: PersonDao = mock()
    val subject = PersonService(dao)

    @Test
    fun `add person if it does not already exist`() {
        whenever(dao.exists(any())).thenReturn(false)

        val name = "Joe"
        val result = subject.add(name)

        result.shouldBeTrue()

        verify(dao).exists(name)
        verify(dao).save(name)
    }

    @Test
    fun `does not add person if it already exists`() {
        whenever(dao.exists(any())).thenReturn(true)

        val name = "Joe"
        val result = subject.add(name)

        result.shouldBeFalse()

        verify(dao).exists(name)
        verify(dao, never()).save(name)
    }
}
