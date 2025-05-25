package com.jbrigido.creatinglists.domain.Person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "first_name") val name: String?,
    @ColumnInfo(name = "age") val age: String?,
    @ColumnInfo(name = "gender") val gender: String?
) {
    override fun toString(): String {
        return "Name: $name"
    }
}