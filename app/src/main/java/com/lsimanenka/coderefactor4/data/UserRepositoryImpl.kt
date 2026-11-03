package com.lsimanenka.coderefactor4.data

import com.lsimanenka.coderefactor4.domain.UserRepository
import com.lsimanenka.coderefactor4.presentation.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun getUsers(): List<User> {
        val users = listOf(
            User(1, "A"),
            User(2, "B"),
            User(3, "C"),
            User(4, "D"),
            User(5, "E"),
            User(6, "F"),
            User(7, "G"),
            User(21, "A2"),
            User(22, "B2"),
            User(23, "C2"),
            User(24, "D2"),
            User(25, "E2"),
            User(26, "F2"),
            User(27, "G2"),
            User(41, "A4"),
            User(42, "B4"),
            User(43, "C4"),
            User(44, "D4"),
            User(45, "E4"),
            User(46, "F4"),
            User(47, "G4"),
            )
        return users
    }
}