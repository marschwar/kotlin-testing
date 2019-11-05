package com.github.marschwar.kotlin.testing

import org.springframework.stereotype.Repository

@Repository
open class PersonDao {
    private val data = mutableSetOf<String>()
    open fun exists(name: String): Boolean = data.contains(name)
    open fun save(name: String) {
        data.add(name)
    }
}
