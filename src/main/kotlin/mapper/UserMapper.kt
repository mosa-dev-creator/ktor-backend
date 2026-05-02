package com.example.mapper

import com.example.database.UsersTable
import com.example.model.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUser() = User(
    id = this[UsersTable.id],
    name = this[UsersTable.name],
    email = this[UsersTable.email]
)