package com.example.routes

import com.example.model.CreateCourse
import com.example.repository.CourseRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseRoutes(courseRepository: CourseRepository) {

    route("/courses") {

        post {
            val request = call.receive<CreateCourse>()

            val course = courseRepository.createCourse(request.title)

            call.respond(HttpStatusCode.Created, course)
        }

        get {
            val courses = courseRepository.getAllCourses()
            call.respond(courses)
        }
    }
}