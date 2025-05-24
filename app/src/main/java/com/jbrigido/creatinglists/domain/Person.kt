package com.jbrigido.creatinglists.domain

data class Person(val name: String, val age: String, val gender: String) {
    override fun toString(): String {
        return "Name: $name"
    }
}