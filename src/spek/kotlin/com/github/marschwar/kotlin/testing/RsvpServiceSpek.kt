package com.github.marschwar.kotlin.testing

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe


internal object RsvpServiceSpek : Spek({
    describe("Guest is responding") {
        val name = "Joe"
        context("without previous responses to this event") {
            val subject by memoized(CachingMode.EACH_GROUP) { RsvpService() }

            context("with a positive response") {

                beforeGroup { subject.respond(name, true) }

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

                beforeGroup { subject.respond(name, false) }

                it("does not change the guest count") {
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

        context("with a previous positive response") {
            val subject by memoized(CachingMode.EACH_GROUP) {
                val subject = RsvpService()
                subject.respond(name, true)
                subject
            }
            val previousGuestCount = 1

            context("changing to a negative response") {

                beforeGroup { subject.respond(name, false) }

                it("decreases the guest count by one") {
                    assertThat(subject.guestCount).isEqualTo(previousGuestCount - 1)
                }

                it("keeps the person marked a responder") {
                    assertThat(subject.hasResponded(name)).isTrue()
                }

                it("marks the person as a non participating guest") {
                    assertThat(subject.isParticipating(name)).isFalse()
                }
            }
        }

        context("with a previous negative response") {
            val subject by memoized(CachingMode.EACH_GROUP) {
                val subject = RsvpService()
                subject.respond(name, false)
                subject
            }
            val previousGuestCount = 0

            context("changing it to a positive response") {

                beforeGroup { subject.respond(name, true) }

                it("increases the guest count by one") {
                    assertThat(subject.guestCount).isEqualTo(previousGuestCount + 1)
                }

                it("keeps the person marked a responder") {
                    assertThat(subject.hasResponded(name)).isTrue()
                }

                it("marks the person as a participating guest") {
                    assertThat(subject.isParticipating(name)).isTrue()
                }
            }
        }
    }
})
