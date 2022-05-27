package com.soundscape.ui

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