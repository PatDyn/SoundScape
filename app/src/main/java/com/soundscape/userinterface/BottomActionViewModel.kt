package com.soundscape.userinterface

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class BottomActionViewModel : ViewModel() {

    private val _views = mutableStateMapOf(
            "Map" to true,
            "Discover" to false,
            "Saved" to false,
            "More" to false)

    val views: Map<String, Boolean>
        get() = _views

    fun toggleExclusive(key: String) {
        for (elem in _views.keys) {
            _views[elem] = false
        }
        _views[key] = true
    }
}