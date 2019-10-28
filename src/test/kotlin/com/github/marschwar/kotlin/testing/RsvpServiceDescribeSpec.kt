package com.github.marschwar.kotlin.testing

import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec

class RsvpServiceDescribeSpec : DescribeSpec({
    describe("Guest is responding") {
        val name = "Joe"
        context("without previous responses to this event") {

            context("with a positive response") {
                val subject = RsvpService()
                subject.respond(name, true)

                it("increases the guest count to one") {
                    subject.guestCount shouldBe 1
                }

                it("marks the person as a responder") {
                    subject.hasResponded(name) shouldBe true
                }

                it("marks the person as a participating guest") {
                    subject.isParticipating(name) shouldBe true
                }
            }

            context("with a negative response") {
                val subject = RsvpService()
                subject.respond(name, false)

                it("does not change the guest count") {
                    subject.guestCount shouldBe 0
                }

                it("marks the person as a responder") {
                    subject.hasResponded(name) shouldBe true
                }

                it("marks the person as a non participating guest") {
                    subject.isParticipating(name) shouldBe false
                }
            }
        }
    }
})
