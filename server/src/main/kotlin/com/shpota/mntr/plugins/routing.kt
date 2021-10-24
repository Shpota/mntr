package com.shpota.mntr.plugins

import com.shpota.mntr.db.Services
import com.shpota.mntr.models.CreateServiceRequest
import com.shpota.mntr.models.Service
import com.shpota.mntr.models.ServiceStatus.UNKNOWN
import com.shpota.mntr.models.asServiceStatus
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

fun Application.configureRouting(sessions: Set<DefaultWebSocketServerSession>) {
    routing {
        get("/services") {
            val services = transaction {
                Services.selectAll().map {
                    Service(
                        it[Services.id],
                        it[Services.name],
                        it[Services.url],
                        it[Services.createdDate],
                        it[Services.status].asServiceStatus()
                    )
                }
            }
            call.respond(services)
        }
        post("/services") {
            val request = call.receive<CreateServiceRequest>()
            val service = Service(
                name = request.name,
                url = request.url,
                createdDate = Instant.now().toEpochMilli(),
                status = UNKNOWN
            )
            transaction {
                Services.insert {
                    it[id] = service.id
                    it[name] = service.name
                    it[url] = service.url
                    it[createdDate] = service.createdDate
                    it[status] = service.status.name
                }
            }
            launch {
                val json = Json.encodeToString(service)
                sessions.forEach { it.send(json) }
            }
            call.respond(HttpStatusCode.Created, service)
        }
    }
}
