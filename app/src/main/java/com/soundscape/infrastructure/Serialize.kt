package com.soundscape.infrastructure

import android.content.Context
import com.soundscape.domain.Bar
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun getBarList(context: Context) : List<Bar> {
    val text = context.assets.open("bars_tuebingen.jsonl").bufferedReader().readText()
    val lines = text.split("\r?\n|\r".toRegex()).toTypedArray()
    return lines.map { Json.decodeFromString(it) }
}
