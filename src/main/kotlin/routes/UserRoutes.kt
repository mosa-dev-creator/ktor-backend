package com.example.routes

import com.example.model.CreateUser
import com.example.model.UpdateUser
import com.example.repository.UserRepository
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.configureRouting(
    repository: UserRepository,
    userService: UserService
) {
    route("/users") {

        get {
            call.respond(repository.getAll())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val user = repository.getById(id)
                ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(user)
        }

        post {
            try {
                // Lire le body JSON
                val user = call.receive<CreateUser>()


                // Logique métier
                val created = userService.create(user)

                // Réponse HTTP
                call.respond(HttpStatusCode.Created, created)

            } catch (e: IllegalArgumentException) {

                call.respond(HttpStatusCode.BadRequest, mapOf(
                    "error" to e.message
                ))

            } catch (e: Exception) {

                call.respond(HttpStatusCode.InternalServerError, mapOf(
                    "error" to "Something went wrong"
                ))
            }
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest)

            val user = call.receive<UpdateUser>()
            val updated = repository.update(id, user)

            if (updated)
                call.respond(HttpStatusCode.OK)
            else
                call.respond(HttpStatusCode.NotFound)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest)

            val deleted = repository.delete(id)

            if (deleted)
                call.respond(HttpStatusCode.NoContent)
            else
                call.respond(HttpStatusCode.NotFound)
        }
    }
}