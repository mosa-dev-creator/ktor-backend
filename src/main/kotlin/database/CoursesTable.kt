package com.example.database

import org.jetbrains.exposed.dao.id.IntIdTable

object CoursesTable : IntIdTable("courses") {
    val title = varchar("title", 100)
}