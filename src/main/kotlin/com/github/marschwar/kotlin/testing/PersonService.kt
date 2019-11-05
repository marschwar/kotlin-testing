package com.github.marschwar.kotlin.testing

import org.springframework.stereotype.Service

@Service
class PersonService(private val dao: PersonDao) {

    fun add(name: String): Boolean {
        if (!dao.exists(name)) {
            dao.save(name)
            return true
        }
        return false
    }
}
