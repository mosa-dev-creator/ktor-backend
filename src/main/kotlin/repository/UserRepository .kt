package com.example.repository

import com.example.database.UsersTable
import com.example.model.CreateUser
import com.example.model.UpdateUser
import com.example.model.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import com.example.mapper.toUser

class UserRepository {

    fun getAll(): List<User> = transaction {
        UsersTable.selectAll().map { it.toUser() }
    }

    fun getById(id: Int): User? = transaction {
        UsersTable.select { UsersTable.id eq id }
            .map { it.toUser() }
            .singleOrNull()
    }

    fun create(user: CreateUser): User = transaction {
        val id = UsersTable.insert {
            it[name] = user.name
            it[email] = user.email
        } get UsersTable.id

        User(id, user.name, user.email)
    }

    fun update(id: Int, user: UpdateUser): Boolean = transaction {
        UsersTable.update({ UsersTable.id eq id }) {
            it[name] = user.name
            it[email] = user.email
        } > 0
    }

    fun delete(id: Int): Boolean = transaction {
        UsersTable.deleteWhere { UsersTable.id eq id } > 0
    }


}