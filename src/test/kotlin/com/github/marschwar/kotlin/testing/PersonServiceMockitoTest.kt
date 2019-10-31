package com.github.marschwar.kotlin.testing

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.matchers.boolean.shouldBeFalse
import io.kotlintest.matchers.boolean.shouldBeTrue
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

internal class PersonServiceMockitoTest {

    lateinit var subject: PersonService

    lateinit var dao: PersonDao

    @BeforeTest
    fun setupMock() {
        dao = mock()
        subject = PersonService(dao)
    }

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
