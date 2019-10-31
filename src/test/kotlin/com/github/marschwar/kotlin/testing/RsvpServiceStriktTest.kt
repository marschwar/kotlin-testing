package com.github.marschwar.kotlin.testing

import strikt.api.expect
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.contains
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEmpty
import strikt.assertions.isTrue
import strikt.assertions.startsWith
import kotlin.test.Test

class RsvpServiceStriktTest {
    val name = "Joe"
    val name2 = "Jironemo"

    @Test
    fun `a positive response without previous responses`() {
        val subject = RsvpService()
        subject.respond(name, true)
        subject.respond(name2, true)

        expectThat(subject.getGuestList())
            .isNotEmpty()
            .hasSize(2)
            .all {
                startsWith("J")
                contains("o")
                contains("e")

            }

        expect {
            that(subject.guestCount).isEqualTo(2)
            that(subject.hasResponded(name)).isTrue()
            that(subject.isParticipating(name)).isTrue()
        }
    }
}
