package com.example.database

import org.jetbrains.exposed.sql.Table

object StudentCoursesTable : Table("student_courses") {
    val student = reference("student_id", StudentsTable)
    val course = reference("course_id", CoursesTable)

    override val primaryKey = PrimaryKey(student, course)
}