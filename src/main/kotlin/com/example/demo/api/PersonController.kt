package com.example.demo.api

import com.example.demo.model.Person
import com.example.demo.service.PersonService
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RequestMapping("api/v1/person")
@RestController
class PersonController(@Autowired private val personService: PersonService) {

    @PostMapping
    fun addPerson(@Valid @NotNull @RequestBody person: Person) {
        personService.addPerson(person)
    }

    @GetMapping
    fun getAllPeople(): List<Person> {
        return personService.getAllPeople()
    }

    @GetMapping(path = arrayOf("{id}"))
    fun getPersonById(@PathVariable("id") id: UUID): Person? {
        return personService.getPersonByID(id).orElse(null)
    }

    //    @DeleteMapping(path = arrayOf("id"))
    @DeleteMapping(value = ["{id}"])
    fun deletePersonById(@PathVariable("id") id: UUID): Int {
        return personService.deletePerson(id)
    }

    @PutMapping(value = ["{id}"])
    fun updatePerson(@PathVariable("id") id: UUID, @Valid @NotNull @RequestBody person: Person): Int {
        return personService.updatePerson(id, person)
    }


}