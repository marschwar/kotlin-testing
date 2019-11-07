package com.github.marschwar.kotlin.testing

import com.nhaarman.mockitokotlin2.*
import io.kotlintest.Matcher
import io.kotlintest.MatcherResult
import io.kotlintest.matchers.boolean.shouldBeFalse
import io.kotlintest.matchers.boolean.shouldBeTrue
import io.kotlintest.should
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

    @Test
    fun `the guest list cannot be empty when a guest as sent a positive response`() {
        val subject = RsvpService()

        subject.respond("Joe", true)

        subject.hasGuests()
    }

    private fun haveGuests() = object : Matcher<RsvpService> {
        override fun test(value: RsvpService) = MatcherResult(value.guestCount > 0, "service should return some guests", "service should not return any guests but returned ${value.guestCount}")
    }

    private fun RsvpService.hasGuests() = this should haveGuests()
}
