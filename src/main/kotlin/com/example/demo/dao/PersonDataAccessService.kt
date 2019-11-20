package com.example.demo.dao

import com.example.demo.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*


@Repository("postgress")
class PersonDataAccessService : PersonDao {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    override fun insertPerson(id: UUID, person: Person): Int {
        val sql = "" +
                "INSERT INTO person VALUES (?, ?)"
        val res = jdbcTemplate.update(
                sql,
                id.toString(),
                person.Name
        )
        println("insertPerson id = " + id.toString() + " | " + person.toString() + " | res = " + res)
        return res
    }

    override fun selectAllPeople(): List<Person> {
        val sql = "select * from person"
        return jdbcTemplate.query(sql) { rs: ResultSet, rowNum: Int ->
            val uuid = UUID.fromString(rs.getString(("id")))
            val name = rs.getString("name")
            return@query Person(uuid, name)
        }
    }

    override fun deletePersonByID(id: UUID): Int {
        val sql = "delete from person where id = ?"
        return jdbcTemplate.update(sql, id.toString())
    }

    override fun updatePersonByID(id: UUID, person: Person): Int {
        val sql = "update person set name = ? where id = ?"
        val res = jdbcTemplate.update(sql, person.Name, id.toString())

        println("updatePersonByID sql = " + sql + " | id = " + id + " | " + person.toString() + " | res = " + res)

        return res;
    }

    override fun selectPersonByID(id: UUID): Optional<Person> {
        val sql = "select * from person where id = '${id.toString()}'"
        val persons = jdbcTemplate.query(sql) { rs: ResultSet, rowNum: Int ->
            val person = Person(UUID.fromString(rs.getString("id")), rs.getString("name"))
            return@query person
        }

        val len = persons?.size
        var res: Optional<Person>? = null

        println("selectPersonByID id = " + id.toString() + " # len = " + len)

        if (len > 0) {
            res = Optional.of(persons?.get(0)) ?: Optional.empty()
            return res
        }

//        println("selectPersonByID id = " + id.toString() + " | sql = " + sql + " | res = " + res)

        return Optional.empty()
    }

}