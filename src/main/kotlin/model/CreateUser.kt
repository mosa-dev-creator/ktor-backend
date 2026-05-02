package com.example.model

import kotlinx.serialization.Serializable

//DTO (Data Transfert Object) pour entrée (Create)
@Serializable
data class CreateUser(
    val name: String,
    val email: String
)

/**
 * sécurisé
 * propre
 * maintenable
 */