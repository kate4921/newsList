package com.example.practise2023.presentation.home

sealed class HomeEvent {

    data class UpdateCategory(val searchQuery: String, val categoryIndex: Int) : HomeEvent()

    object LoadStuff : HomeEvent()
}
