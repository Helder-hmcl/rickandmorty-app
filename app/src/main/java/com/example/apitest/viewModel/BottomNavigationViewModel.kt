package com.example.apitest.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BottomNavigationViewModel : ViewModel() {
    val bottomMenuSelected = mutableStateOf(BottomIcons.HOME)

    fun setBottomMenuSelected(icon: BottomIcons) {
        bottomMenuSelected.value = icon
    }
}

enum class BottomIcons {
    HOME,
    EPISODES,
    CHARACTERS,
    FAVORITES,
}
