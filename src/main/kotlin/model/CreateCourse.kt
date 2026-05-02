package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateCourse(
    val title: String
)