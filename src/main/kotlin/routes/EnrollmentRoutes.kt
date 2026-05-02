package com.example.routes

import com.example.model.EnrollmentRequest
import com.example.service.EnrollmentService
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.enrollmentRoutes(
    enrollmentService: EnrollmentService
) {
    post("/enroll") {

        val request = call.receive<EnrollmentRequest>()

        enrollmentService.enrollStudent(
            request.studentId,
            request.courseId
        )

        call.respond("Student enrolled successfully")
    }

    get("/students/{id}/courses") {

        val studentId = call.parameters["id"]!!.toInt()

        val courses = enrollmentService.getCoursesForStudent(studentId)

        call.respond(courses)
    }
}