package com.soundscape.domain

import java.util.*

// Description
// Start- and End Time
// HostLocation


class Event(location: Location) {

    val eventName: String
        get() {
            TODO()
        }

    val description: String
        get() {
            TODO()
    }

    val startEndTime: Date
        get() {
            TODO()
        }

    val hostLocation = location.name
}