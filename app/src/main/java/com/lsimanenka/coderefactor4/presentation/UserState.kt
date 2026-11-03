package com.lsimanenka.coderefactor4.presentation

sealed interface UserState {
    data object Loading : UserState
    data class Success(val users: List<User>) : UserState
    data class Error(val exception: Exception) : UserState
}