package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val name: String
)
