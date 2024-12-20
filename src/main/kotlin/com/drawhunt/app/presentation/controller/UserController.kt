package com.drawhunt.app.presentation.controller

import com.drawhunt.app.presentation.dto.UserRegistrationDTO
import com.drawhunt.app.usecase.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val authService: AuthService
) {
    @PostMapping("/create")
    fun registerUser(@Valid @RequestBody userDTO: UserRegistrationDTO): ResponseEntity<Any> {
        return try {
            authService.registerNewUser(userDTO)
            ResponseEntity.status(HttpStatus.CREATED).body(mapOf("message" to "User created successfully"))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to e.message))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "Server error"))
        }
    }
}