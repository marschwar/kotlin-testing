package com.github.marschwar.kotlin.testing

import assertk.Assert
import assertk.all
import assertk.assertThat
import assertk.assertions.each
import assertk.assertions.hasSize
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotEmpty
import assertk.assertions.prop
import io.kotlintest.matchers.haveSubstring
import io.kotlintest.matchers.startWith
import kotlin.test.Test

class RsvpServiceAssertKTest {
    val name = "Joe"
    val name2 = "Jironemo"

    @Test
    fun `the guest list cannot be empty when a guest as sent a positive response`() {
        val subject = RsvpService()

        subject.respond(name, true)

        assertThat(subject).hasGuests()
    }

    @Test
    fun `multiple positive responses with previous responses`() {
        val subject = RsvpService()

        subject.respond(name, true)
        subject.respond(name2, true)

        assertThat(subject.getGuestList()).all {
            isNotEmpty()
            hasSize(2)
            each {
                startWith("J")
                haveSubstring("o")
            }
        }
    }

}

private fun Assert<RsvpService>.hasGuests() {
    prop("guestCount", RsvpService::guestCount).isGreaterThan(0)
}
