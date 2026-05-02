package com.example.mapper

import com.example.database.StudentsTable
import com.example.model.Student
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toStudent(): Student {
    return Student(
        id = this[StudentsTable.id].value,
        name = this[StudentsTable.name]
    )
}