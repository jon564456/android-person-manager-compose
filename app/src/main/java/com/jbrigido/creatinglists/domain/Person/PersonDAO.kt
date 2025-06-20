package com.jbrigido.creatinglists.domain.Person

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDAO {

    @Query("SELECT *  FROM Person")
    fun getAll(): Flow<List<Person>>

    @Insert
    suspend fun insert(vararg person: Person)

    @Delete
    fun delete(person: Person)

}