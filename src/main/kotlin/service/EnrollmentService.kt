package com.example.service

import com.example.repository.CourseRepository
import com.example.repository.EnrollmentRepository
import com.example.repository.StudentRepository

class EnrollmentService(
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository,
    private val enrollmentRepository: EnrollmentRepository
) {
    fun enrollStudent(studentId: Int, courseId: Int) {

        // 1. Vérifier si étudiant existe
        val student = studentRepository.getAllStudents()
            .find { it.id == studentId }
            ?: throw Exception("Student not found")

        // 2. Vérifier si cours existe
        val course = courseRepository.getAllCourses()
            .find { it.id == courseId }
            ?: throw Exception("Course not found")

        // 3. Vérifier si déjà inscrit
        val courses = enrollmentRepository.getCoursesForStudent(studentId)

        if (courses.any { it.id == courseId }) {
            throw Exception("Already enrolled")
        }

        // 4. Faire l’inscription
        enrollmentRepository.enrollStudent(studentId, courseId)
    }

    fun getCoursesForStudent(studentId: Int) =
        enrollmentRepository.getCoursesForStudent(studentId)

    fun getStudentsForCourse(courseId: Int) =
        enrollmentRepository.getStudentsForCourse(courseId)

}