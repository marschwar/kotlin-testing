package com.github.marschwar.kotlin.testing

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature


internal object RsvpServiceFeature : Spek({
    Feature("Guest sends a positive response") {
        val subject by memoized { RsvpService() }
        val name = "Joe"

        Scenario("while the guest list is empty") {

            When("responding positively") {
                subject.respond(name, true)
            }

            Then("the guest count is increased by one") {
                assertThat(subject.guestCount).isOne()
            }

            And("the person is marked as a responder") {
                assertThat(subject.hasResponded(name)).isTrue()
            }

            And("the person is marked as a participating guest") {
                assertThat(subject.isParticipating(name)).isTrue()
            }
        }

        Scenario("when the user had already submitted a negative response") {
            lateinit var initialGuestCount: Number

            Given("a negative initial response by the user") {
                subject.respond(name, false)
                initialGuestCount = subject.guestCount
            }

            When("responding positively") {
                subject.respond(name, true)
            }

            Then("the guest count is increased by one") {
                assertThat(subject.guestCount).isEqualTo(initialGuestCount.toInt() + 1)
            }

            And("the person is marked as a responder") {
                assertThat(subject.hasResponded(name)).isTrue()
            }

            And("the person is marked as a participating guest") {
                assertThat(subject.isParticipating(name)).isTrue()
            }
        }

        Scenario("when the user had already submitted a positive response") {
            lateinit var initialGuestCount: Number

            Given("a positive initial response by the user") {
                subject.respond(name, true)
                initialGuestCount = subject.guestCount
            }

            When("responding positively") {
                subject.respond(name, true)
            }

            Then("the guest count remains unchanged") {
                assertThat(subject.guestCount).isEqualTo(initialGuestCount)
            }

            And("the person remains marked as a responder") {
                assertThat(subject.hasResponded(name)).isTrue()
            }

            And("the person remains marked as a participating guest") {
                assertThat(subject.isParticipating(name)).isTrue()
            }
        }
    }
})