package com.example.model

import kotlinx.serialization.Serializable

//Model pour sortie (User)
@Serializable
data class User(
    val id: Int? = null,
    val name: String,
    val email: String
)