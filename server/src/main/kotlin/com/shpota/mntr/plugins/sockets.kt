package com.shpota.mntr.plugins

import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.time.Duration

fun Application.configureSockets(connections: MutableSet<DefaultWebSocketServerSession>) {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
    }

    routing {
        webSocket("/services") {
            connections += this
            try {
                for (frame in incoming) {
                    when (frame) {
                        is Frame.Ping -> outgoing.send(Frame.Pong("OK".toByteArray()))
                        is Frame.Close -> connections -= this
                    }
                }
            } finally {
                connections -= this
            }
        }
    }
}
