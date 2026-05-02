package com.example.database

import org.jetbrains.exposed.dao.id.IntIdTable

object StudentsTable : IntIdTable("students") {
    val name = varchar("name", 100)
}