package com.example.repository

import com.example.database.CoursesTable
import com.example.mapper.toCourse
import com.example.model.Course
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CourseRepository {

    fun createCourse(title: String): Course {
        return transaction {
            val id = CoursesTable.insertAndGetId {
                it[CoursesTable.title] = title
            }

            Course(id.value, title)
        }
    }

    fun getAllCourses(): List<Course> {
        return transaction {
            CoursesTable
                .selectAll()
                .map { it.toCourse() }
        }
    }
}