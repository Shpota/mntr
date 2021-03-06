package com.shpota.mntr

import com.shpota.mntr.plugins.configureCORS
import com.shpota.mntr.plugins.configureRouting
import com.shpota.mntr.plugins.configureSerialization
import com.shpota.mntr.plugins.configureSockets
import com.shpota.mntr.plugins.startDatabase
import com.shpota.mntr.schedule.scheduleServiceVerification
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.websocket.*
import java.util.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        val sessions: MutableSet<DefaultWebSocketServerSession> = Collections.synchronizedSet(LinkedHashSet())
        startDatabase()
        configureCORS()
        configureSerialization()
        configureRouting(sessions)
        configureSockets(sessions)
        scheduleServiceVerification(sessions, intervalSeconds = 10)
    }.start(wait = true)
}
