package com.example.repository

import com.example.database.CoursesTable
import com.example.database.StudentCoursesTable
import com.example.database.StudentsTable
import com.example.mapper.toCourse
import com.example.mapper.toStudent
import com.example.model.Course
import com.example.model.Student
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class EnrollmentRepository {

    fun enrollStudent(studentId: Int, courseId: Int) {
        transaction {
            StudentCoursesTable.insert {
                it[student] = studentId
                it[course] = courseId
            }
        }
    }

    fun getCoursesForStudent(studentId: Int): List<Course> {
        return transaction {
            (CoursesTable innerJoin StudentCoursesTable)
                .select { StudentCoursesTable.student eq studentId }
                .map { it.toCourse() }
        }
    }

    fun getStudentsForCourse(courseId: Int): List<Student> {
        return transaction {
            (StudentsTable innerJoin StudentCoursesTable)
                .select { StudentCoursesTable.course eq courseId }
                .map { it.toStudent() }
        }
    }
}