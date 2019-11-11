package com.example.demo.service

import com.example.demo.dao.PersonDao
import com.example.demo.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Service
class PersonService(@Autowired @Qualifier("postgress") private val personDao: PersonDao) {

    fun addPerson(person: Person): Int {
        return personDao.insertPerson(person)
    }

    fun getAllPeople(): List<Person> {
        return personDao.selectAllPeople()
    }

    fun getPersonByID(id: UUID): Optional<Person> {
        return personDao.selectPersonByID((id))
    }

    fun deletePerson(id: UUID): Int {
        return personDao.deletePersonByID(id)
    }

    fun updatePerson(id: UUID, person: Person): Int {
        return personDao.updatePersonByID(id, person)
    }

}