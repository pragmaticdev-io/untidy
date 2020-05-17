package io.pragmaticdev.untidy.extension

import java.security.MessageDigest

fun String.md5(): String {
    return hashString(this, "MD5")
}

fun String.sha256(): String {
    return hashString(this, "SHA-256")
}

private fun hashString(input: String, algorithm: String) = MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
        .joinToString()