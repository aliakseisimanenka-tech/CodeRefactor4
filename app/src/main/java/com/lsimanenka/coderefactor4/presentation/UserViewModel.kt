package com.lsimanenka.coderefactor4.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsimanenka.coderefactor4.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository : UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UserState>(UserState.Loading)
    val state = _state.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                _state.update {
                    UserState.Loading
                }
                val usersList = userRepository.getUsers()
                _state.update {
                    UserState.Success(usersList)
                }
            } catch (e : Exception) {
                _state.update {
                    UserState.Error(e)
                }
            }
        }
    }
}