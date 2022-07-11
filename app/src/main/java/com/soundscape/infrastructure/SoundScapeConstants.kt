package com.soundscape.infrastructure

import com.soundscape.SoundScapeScreen

object SoundScapeConstants {

    private var _navStartDestination = SoundScapeScreen.StartOff.name

    fun getNavStartDestination(): String {
        return _navStartDestination
    }

    fun setNavStartDestination(destination: String) {
        _navStartDestination = destination
    }


}