package com.lsimanenka.coderefactor4.domain

import com.lsimanenka.coderefactor4.presentation.User

interface UserRepository {
    fun getUsers() : List<User>
}