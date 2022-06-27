package com.soundscape.infrastructure

import java.security.MessageDigest

fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun String.toHashed(type: String): String {
    return MessageDigest
        .getInstance(type)
        .digest(this.toByteArray())
        .fold("") { str, it -> str + "%02x".format(it) }
}