package com.shpota.mntr

import com.shpota.mntr.plugins.configureRouting
import com.shpota.mntr.plugins.configureSockets
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.shpota.mntr.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSockets()
    }.start(wait = true)
}
