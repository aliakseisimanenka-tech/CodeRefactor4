package com.lsimanenka.coderefactor4.presentation

import com.lsimanenka.coderefactor4.presentation.User

sealed interface UserState {
    data object Loading : UserState
    data class Success(val users: List<User>) : UserState
    data class Error(val exception: Exception) : UserState
}