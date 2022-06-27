package com.soundscape.infrastructure

import android.content.Context
import com.soundscape.domain.Location
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun getBarList(context: Context) : List<Location> {
    val text = context.assets.open("locations_tuebingen.jsonl").bufferedReader().readText()
    val lines = text.split("\r?\n|\r".toRegex()).toTypedArray()
    return lines.map { Json.decodeFromString(it) }
}
