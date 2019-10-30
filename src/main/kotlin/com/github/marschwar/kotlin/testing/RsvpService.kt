package com.github.marschwar.kotlin.testing

class RsvpService {
    private val guests = mutableMapOf<String, Boolean>()

    val guestCount: Int
        get() = guests.count { it.value }

    fun respond(name: String, participating: Boolean) {
        guests[name] = participating
    }

    fun hasResponded(name: String): Boolean = guests.containsKey(name)

    fun isParticipating(name: String): Boolean = guests[name] ?: false

    fun getGuestList(): Set<String> = guests.filter { it.value }.keys
}
