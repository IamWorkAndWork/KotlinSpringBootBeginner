package com.example.demo.dao

import com.example.demo.model.Person
import org.springframework.stereotype.Repository
import java.util.*

@Repository("fakeDao")
class FakePersonDataAccessService : PersonDao {

    var DB = arrayListOf<Person>()

    override fun insertPerson(id: UUID, person: Person): Int {
        DB.add(Person(id, person.Name))
        return 1
    }

    override fun selectAllPeople(): List<Person> {
        return DB
    }

    override fun deletePersonByID(id: UUID): Int {
        val personMaybe = selectPersonByID(id)
        if (personMaybe == null) {
            return 0
        }
        DB.remove(personMaybe.get())
        return 1
    }

    override fun updatePersonByID(id: UUID, newPerson: Person): Int {
//        println("update person = " + person.toString())
        return selectPersonByID(id).map { person ->
            val indexOfPerson = DB.indexOf(person)
//            println("update indexOfPerson = " + indexOfPerson)
            if (indexOfPerson >= 0) {
                DB.set(indexOfPerson, Person(person.id, newPerson.Name))
                1
            } else {
                0
            }
        }.orElse(0)
    }

    override fun selectPersonByID(id: UUID): Optional<Person> {
        return DB.stream()
                .filter { person: Person? ->
                    person?.id?.equals(id)!!
                }
                .findFirst()
    }


}