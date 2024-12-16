package com.drawhunt.app.domain.repository

import com.drawhunt.app.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
    fun findByEmailAddress(emailAddress: String): Optional<User>
}