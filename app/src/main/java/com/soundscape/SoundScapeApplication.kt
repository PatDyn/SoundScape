package com.soundscape

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.soundscape.infrastructure.Model

class SoundScapeApplication : Application() {
    lateinit var model: Model

    override fun onCreate() {
        super.onCreate()
        model = Model(this.applicationContext)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}