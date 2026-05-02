package com.example

import com.example.database.DatabaseFactory
import com.example.repository.CourseRepository
import com.example.repository.EnrollmentRepository
import com.example.repository.StudentRepository
import com.example.repository.UserRepository
import com.example.routes.configureRouting
import com.example.routes.courseRoutes
import com.example.routes.enrollmentRoutes
import com.example.routes.studentRoutes
import com.example.service.EnrollmentService
import com.example.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.routing


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    DatabaseFactory.init()

    // User
    val repository = UserRepository()
    val userService = UserService(repository)

    // Students / Courses
    val studentRepository = StudentRepository()
    val courseRepository = CourseRepository()
    val enrollmentRepository = EnrollmentRepository()

    val enrollmentService = EnrollmentService(
        studentRepository,
        courseRepository,
        enrollmentRepository
    )

    configureSerialization()

    routing {

        configureRouting(repository, userService)

        studentRoutes(studentRepository)
        courseRoutes(courseRepository)

        enrollmentRoutes(enrollmentService)
    }
}