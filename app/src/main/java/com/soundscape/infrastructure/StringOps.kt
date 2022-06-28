package com.soundscape.infrastructure

import android.os.Build
import androidx.annotation.RequiresApi
import android.util.Base64
import java.security.MessageDigest
import java.util.Base64.getUrlEncoder

fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun String.toHashed(type: String): ByteArray {
    return MessageDigest
        .getInstance(type)
        .digest(this.toByteArray())
}

fun ByteArray.toBase64Url(): String {
    return Base64.encodeToString(this, Base64.URL_SAFE)
}