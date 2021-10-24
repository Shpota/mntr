package com.shpota.mntr.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

fun Application.configureCORS() = install(CORS) {
    anyHost()
    header(HttpHeaders.ContentType)
}