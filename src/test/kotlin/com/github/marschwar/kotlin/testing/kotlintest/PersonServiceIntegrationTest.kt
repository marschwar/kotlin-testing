package com.github.marschwar.kotlin.testing.kotlintest

import com.github.marschwar.kotlin.testing.PersonService
import com.github.marschwar.kotlin.testing.RsvpApplication
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.WordSpec
import io.kotlintest.spring.SpringListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [(RsvpApplication::class)])
internal class PersonServiceIntegrationTest : WordSpec() {

    override fun listeners() = listOf(SpringListener)

    @Autowired
    var personService: PersonService? = null

    init {
        "Spring Extension" should {
            "have wired up the functioning service" {
                val result = personService?.add("foo")

                personService shouldNotBe null
                result shouldBe true
            }
        }
    }
}
