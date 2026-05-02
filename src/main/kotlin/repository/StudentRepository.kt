package com.example.repository

import com.example.database.StudentsTable
import com.example.mapper.toStudent
import com.example.model.Student
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class StudentRepository {

    fun createStudent(name: String): Student {
        return transaction {
            val id = StudentsTable.insertAndGetId {
                it[StudentsTable.name] = name
            }

            Student(id.value, name)
        }
    }

    fun getAllStudents(): List<Student> {
        return transaction {
            StudentsTable
                .selectAll()
                .map { it.toStudent() }
        }
    }
}
