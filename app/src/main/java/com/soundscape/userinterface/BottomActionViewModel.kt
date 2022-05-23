package com.soundscape.userinterface

import android.content.Context
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.soundscape.domain.Location
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private fun getBarList(context: Context) : List<Location> {
    val text = context.assets.open("bars_tuebingen.jsonl").bufferedReader().readText()
    val lines = text.split("\r?\n|\r".toRegex()).toTypedArray()
    return lines.map { Json.decodeFromString(it) }
}

class BottomActionViewModel(context: Context) : ViewModel() {

    private val _views = mutableStateMapOf(
            "Map" to true,
            "Discover" to false,
            "Saved" to false,
            "More" to false)

    val views: Map<String, Boolean>
        get() = _views

    private val _locations: List<Location> = getBarList(context)

    val locations
        get() = _locations

    var location : Location? = null

    fun toggleExclusive(key: String) {
        for (elem in _views.keys) {
            _views[elem] = false
        }
        _views[key] = true
    }
}