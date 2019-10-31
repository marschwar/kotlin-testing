package com.github.marschwar.kotlin.testing

class PersonService(private val dao: PersonDao) {

    fun add(name: String): Boolean {
        if (!dao.exists(name)) {
            dao.save(name)
            return true
        }
        return false
    }
}
