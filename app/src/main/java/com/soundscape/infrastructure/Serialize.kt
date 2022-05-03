package com.soundscape.infrastructure

import android.content.Context
import com.soundscape.domain.Bar
import org.json.JSONArray
import org.json.JSONTokener
import java.util.ArrayList

fun getBarList(context: Context) : ArrayList<Bar> {
    val data = context.assets.open("bars_tuebingen.jsonl").bufferedReader().readText()
    val jsonBars = JSONTokener(data).nextValue() as JSONArray
    val barList : ArrayList<Bar> = arrayListOf<Bar>()

    for (i in 0 until jsonBars.length()) {

        val type = jsonBars.getJSONObject(i).getString("type")
        val id = jsonBars.getJSONObject(i).getString("id").toLong()
        val lat = jsonBars.getJSONObject(i).getString("lat").toDouble()
        val lon = jsonBars.getJSONObject(i).getString("lon").toDouble()

        // go into nested structure
        val tags = jsonBars.getJSONObject(i).getJSONObject("tags")
        val name = tags.getString("name")
        /* TODO: Implement opening hours
        val openingHours = try {
            tags.getString("opening_hours")
        } catch (e: NoSuchFieldError ) { null }

         */
        val geohash = jsonBars.getJSONObject(i).getString("geohash")

        barList.add(i, Bar(type, id, name, lat, lon, tags, geohash))
    }
    return barList
}
