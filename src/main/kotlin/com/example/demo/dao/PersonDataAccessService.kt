package com.example.demo.dao

import com.example.demo.model.Person
import org.springframework.stereotype.Repository
import java.util.*

@Repository("postgress")
class PersonDataAccessService : PersonDao {
    override fun insertPerson(id: UUID, person: Person): Int {
        return 0
    }

    override fun selectAllPeople(): List<Person> {
        return listOf(Person(UUID.randomUUID(), "FROM POSTGRES DB"))
    }

    override fun deletePersonByID(id: UUID): Int {
        return 0
    }

    override fun updatePersonByID(id: UUID, person: Person): Int {
        return 0
    }

    override fun selectPersonByID(id: UUID): Optional<Person> {
        return Optional.empty()
    }

}