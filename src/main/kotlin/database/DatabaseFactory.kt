package com.example.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.createMissingTablesAndColumns
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(
            url = "jdbc:sqlite:test.db",
            driver = "org.sqlite.JDBC"
        )

        transaction {
            createMissingTablesAndColumns(
                UsersTable,
                StudentsTable,
                CoursesTable,
                StudentCoursesTable
            )
        }
    }
}