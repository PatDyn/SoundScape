package com.soundscape.userinterface

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.soundscape.domain.Location

class LocationCardViewModel(location: Location? = null) : ViewModel() {

    private var _showDetail = false

    var thisLocation = location

    val showDetail: Boolean
        get() = _showDetail

    fun toggleDetailView() {
        _showDetail = !_showDetail
    }
}