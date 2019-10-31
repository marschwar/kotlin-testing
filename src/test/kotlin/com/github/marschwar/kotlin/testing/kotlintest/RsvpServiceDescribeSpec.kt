package com.github.marschwar.kotlin.testing.kotlintest

import com.github.marschwar.kotlin.testing.RsvpService
import io.kotlintest.inspectors.forAll
import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.matchers.string.shouldStartWith
import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec

class RsvpServiceDescribeSpec : DescribeSpec({
    describe("Guest is responding") {
        val name = "Joe"
        val name2 = "Jironemo"
        context("without previous responses to this event") {

            context("with a positive response") {
                val subject = RsvpService()
                subject.respond(name, true)
                subject.respond(name2, true)

                it("increases the guest count to two") {
                    subject.guestCount shouldBe 2
                }

                it("marks the person as a responder") {
                    subject.hasResponded(name) shouldBe true
                }

                it("marks the person as a participating guest") {
                    subject.isParticipating(name) shouldBe true
                }
                it("all participating persons start with letter J and contains o and e") {
                    subject.getGuestList().forAll {
                        it.shouldStartWith("J")
                        it.shouldContain("o")
                        it.shouldContain("e")
                    }
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
