package com.github.marschwar.kotlin.testing

import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec

class RsvpServiceBehaviorSpec : BehaviorSpec({
    Given("A positive initial response") {
        val subject = RsvpService()
        val name = "Joe"
        val initialGuestCount = 0

        When("responding positively") {
            subject.respond(name, true)

            Then("the guest count is increased by one") {
                subject.guestCount shouldBe (initialGuestCount + 1)
            }
            And("he person is marked as a responder") {
                subject.hasResponded(name) shouldBe true
            }
            And("the person is marked as a participating guest") {
                subject.isParticipating(name) shouldBe true
            }
        }
    }
})
