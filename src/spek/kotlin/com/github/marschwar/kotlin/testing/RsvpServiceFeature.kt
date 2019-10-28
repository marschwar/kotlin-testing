package com.github.marschwar.kotlin.testing

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature


internal object RsvpServiceFeature : Spek({
    Feature("Guest sends a response") {
        val subject by memoized { RsvpService() }
        val name = "Joe"

        Scenario("A positive initial response") {
            val initialGuestCount = 0

            Given("the user has not yet responded") {}

            When("responding positively") {
                subject.respond(name, true)
            }

            Then("the guest count is increased by one") {
                assertThat(subject.guestCount).isEqualTo(initialGuestCount + 1)
            }

            And("the person is marked as a responder") {
                assertThat(subject.hasResponded(name)).isTrue()
            }

            And("the person is marked as a participating guest") {
                assertThat(subject.isParticipating(name)).isTrue()
            }
        }

        Scenario("A negative initial response") {
            val initialGuestCount = 0

            Given("the user has not yet responded") {}

            When("responding negatively") {
                subject.respond(name, false)
            }

            Then("the guest count remains unchanged") {
                assertThat(subject.guestCount).isEqualTo(initialGuestCount)
            }

            And("the person is marked as a responder") {
                assertThat(subject.hasResponded(name)).isTrue()
            }

            And("the person is marked as a non participating guest") {
                assertThat(subject.isParticipating(name)).isFalse()
            }
        }

        Scenario("the user changes a negative response to a positive one") {
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

        Scenario("the user changes a positive response to a negative one") {
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
