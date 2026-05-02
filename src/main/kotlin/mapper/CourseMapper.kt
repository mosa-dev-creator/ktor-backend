package com.example.mapper

import com.example.database.CoursesTable
import com.example.model.Course
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toCourse(): Course {
    return Course(
        id = this[CoursesTable.id].value,
        title = this[CoursesTable.title]
    )
}