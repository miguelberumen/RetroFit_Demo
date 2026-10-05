package com.cuvalles.retrofit_demo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    var postListState by mutableStateOf<List<DataPost>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            isLoading = true
            try {
                postListState = ApiService.instance.getPosts()
            } catch (e: Exception) {
                // Manejo de excepción
            } finally {
                isLoading = false
            }
        }
    }
}