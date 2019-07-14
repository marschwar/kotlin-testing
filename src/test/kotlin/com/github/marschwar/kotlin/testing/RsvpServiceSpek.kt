package com.github.marschwar.kotlin.testing

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal class RsvpServiceSpek : Spek({
    describe("responding") {
        context("while the guest list is empty") {
            val subject by memoized { RsvpService() }
            val name = "Joe"

            context("with a positive response") {

                beforeEach { subject.respond(name, true) }

                it("increases the guest count to one") {
                    assertThat(subject.guestCount).isOne()
                }

                it("marks the person as a responder") {
                    assertThat(subject.hasResponded(name)).isTrue()
                }

                it("marks the person as a participating guest") {
                    assertThat(subject.isParticipating(name)).isTrue()
                }
            }

            context("with a negative response") {

                beforeEach { subject.respond(name, false) }

                it("does not change the guest") {
                    assertThat(subject.guestCount).isZero()
                }

                it("marks the person as a responder") {
                    assertThat(subject.hasResponded(name)).isTrue()
                }

                it("marks the person as a non participating guest") {
                    assertThat(subject.isParticipating(name)).isFalse()
                }
            }
        }

        context("while guests have already responded") {
            val namePositive = "Joe"
            val nameNegative = "Mike"
            val subject by memoized {
                val subject = RsvpService()
                subject.respond(namePositive, true)
                subject.respond(nameNegative, false)
                subject
            }

            context("when a positive response is changed to a negative") {
                val previousGuestCount = subject.guestCount

                beforeEach { subject.respond(namePositive, false) }

                it("decreases the guest count by one") {
                    assertThat(subject.guestCount).isEqualTo(previousGuestCount - 1)
                }

                it("marks the person as a responder") {
                    assertThat(subject.hasResponded(namePositive)).isTrue()
                }

                it("marks the person as a non participating guest") {
                    assertThat(subject.isParticipating(namePositive)).isFalse()
                }
            }

            context("when a negative response is changed to a positive") {
                val previousGuestCount = subject.guestCount

                beforeEach { subject.respond(nameNegative, true) }

                it("increases the guest count by one") {
                    assertThat(subject.guestCount).isEqualTo(previousGuestCount + 1)
                }

                it("marks the person as a responder") {
                    assertThat(subject.hasResponded(namePositive)).isTrue()
                }

                it("marks the person as a participating guest") {
                    assertThat(subject.isParticipating(namePositive)).isTrue()
                }
            }
        }
    }
})