package com.example.practise2023.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.practise2023.domain.usecases.GetNews
import com.example.practise2023.domain.usecases.SearchNews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNews,
    private val searchNewsUseCase: SearchNews
) : ViewModel() {

    private var _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        val news = getNewsUseCase(
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = news)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateCategory -> {
                _state.value = _state.value.copy(
                    selectedCategoryIndex = event.categoryIndex,
                    searchCategory = event.searchQuery
                )
                searchArticles()
                //loadStuff()
            }

            is HomeEvent.LoadStuff -> {
                loadStuff()
            }
        }
    }

    private fun searchArticles() {
        if(state.value.selectedCategoryIndex == 0){
            val articles = getNewsUseCase(
                sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
            ).cachedIn(viewModelScope)
            _state.value = _state.value.copy(articles = articles)
        } else {
            val articles = searchNewsUseCase(
                category = _state.value.searchCategory
            ).cachedIn(viewModelScope)
            _state.value = _state.value.copy(articles = articles)
        }
    }

    private fun loadStuff() {
        _state.value = _state.value.copy(isLoading = true)

        if(state.value.selectedCategoryIndex == 0){
            val articles = getNewsUseCase(
                sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
            ).cachedIn(viewModelScope)
            _state.value = _state.value.copy(articles = articles, isLoading = false)
        } else {
            val articles = searchNewsUseCase(
                category = _state.value.searchCategory
            ).cachedIn(viewModelScope)
            _state.value = _state.value.copy(articles = articles, isLoading = false)
        }
    }

}