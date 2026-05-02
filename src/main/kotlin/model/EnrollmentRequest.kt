package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class EnrollmentRequest(
    val studentId: Int,
    val courseId: Int
)