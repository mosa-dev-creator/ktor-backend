package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: Int,
    val title: String
)
