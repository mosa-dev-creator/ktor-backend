package com.example.service

import com.example.model.CreateUser
import com.example.model.User
import com.example.repository.UserRepository

class UserService(private val repository: UserRepository) {

    fun create(user: CreateUser): User {

        // 🔍 Validation
        if (user.name.isBlank()) {
            throw IllegalArgumentException("Name cannot be empty")
        }

        if (!user.email.contains("@")) {
            throw IllegalArgumentException("Invalid email")
        }

        // 💼 Logique métier possible ici
        // ex: vérifier si email existe déjà

        return repository.create(user)
    }
}