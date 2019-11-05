package com.github.marschwar.kotlin.testing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal class RsvpApplication

fun main() {
    runApplication<RsvpApplication>()
}
