package com.example.demo.dao

import com.example.demo.model.Person
import java.util.*

interface PersonDao {

    fun insertPerson(id: UUID, person: Person): Int

    fun insertPerson(person: Person): Int {
        val id: UUID = UUID.randomUUID()
        return insertPerson(id, person)
    }

    fun selectAllPeople(): List<Person>

    fun deletePersonByID(id: UUID): Int

    fun updatePersonByID(id: UUID, person: Person): Int

    fun selectPersonByID(id: UUID): Optional<Person>

}
