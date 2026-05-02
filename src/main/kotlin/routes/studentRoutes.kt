package com.example.routes

import com.example.model.CreateStudent
import com.example.repository.StudentRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.studentRoutes(studentRepository: StudentRepository) {

    route("/students") {

        post {
            val request = call.receive<CreateStudent>()

            val student = studentRepository.createStudent(request.name)

            call.respond(HttpStatusCode.Created, student)
        }

        get {
            val students = studentRepository.getAllStudents()
            call.respond(students)
        }
    }
}