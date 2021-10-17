package com.shpota.mntr

import com.shpota.mntr.plugins.configureRouting
import com.shpota.mntr.plugins.configureSerialization
import com.shpota.mntr.plugins.configureSockets
import com.shpota.mntr.plugins.startDatabase
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.websocket.*
import java.util.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        val sessions: MutableSet<DefaultWebSocketServerSession> = Collections.synchronizedSet(LinkedHashSet())
        startDatabase()
        configureSerialization()
        configureRouting(sessions)
        configureSockets(sessions)
    }.start(wait = true)
}
