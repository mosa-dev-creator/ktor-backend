package com.example.model

import kotlinx.serialization.Serializable

//DTO (Data Transfert Object) pour entrée (Update)
@Serializable
data class UpdateUser(
    val name: String,
    val email: String
)

/**
 * sécurisé
 * propre
 * maintenable
 */