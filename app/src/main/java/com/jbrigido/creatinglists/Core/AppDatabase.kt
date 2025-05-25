package com.jbrigido.creatinglists.Core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jbrigido.creatinglists.domain.Person.Person
import com.jbrigido.creatinglists.domain.Person.PersonDAO

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDAO(): PersonDAO
}
