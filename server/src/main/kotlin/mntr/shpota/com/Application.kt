package mntr.shpota.com

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mntr.shpota.com.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSockets()
    }.start(wait = true)
}
