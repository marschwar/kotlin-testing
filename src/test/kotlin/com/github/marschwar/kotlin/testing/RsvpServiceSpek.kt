package com.github.marschwar.kotlin.testing

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal class RsvpServiceSpek : Spek({
    describe("responding") {
        context("with an empty guest list") {
            val subject by memoized { RsvpService() }

            context("a positive response") {
                val name = "Joe"
                val previousGuestCount = subject.guestCount

                beforeEach { subject.respond(name, true) }

                it("increases the guest count by one") {
                    assertThat(subject.guestCount).isEqualTo(previousGuestCount + 1)
                }

                it("marks the person as a responder") {
                    assertThat(subject.hasResponded(name)).isTrue()
                }

                it("marks the person as a participating guest") {
                    assertThat(subject.isParticipating(name)).isTrue()
                }
            }
        }
    }
})