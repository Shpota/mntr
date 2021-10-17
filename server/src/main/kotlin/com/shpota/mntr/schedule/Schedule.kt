package com.shpota.mntr.schedule

import com.shpota.mntr.db.Services
import com.shpota.mntr.models.Service
import com.shpota.mntr.models.ServiceStatus
import com.shpota.mntr.models.ServiceStatus.AVAILABLE
import com.shpota.mntr.models.ServiceStatus.UNAVAILABLE
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.cio.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update


fun Application.scheduleServiceVerification(sessions: Set<DefaultWebSocketServerSession>, intervalSeconds: Long) {
    val client = HttpClient(CIO)
    launch {
        while (true) {
            transaction {
                Services.selectAll().forEach { row ->
                    val serviceId = row[Services.id]
                    val name = row[Services.name]
                    val url = row[Services.url]
                    val status = row[Services.status]
                    val createdDate = row[Services.createdDate]
                    launch {
                        try {
                            val response: HttpResponse = client.get(url)
                            val newStatus = if (response.status == OK) AVAILABLE else UNAVAILABLE
                            if (status != newStatus.name) {
                                updateStatus(serviceId, newStatus)
                                val json = Json.encodeToString(Service(serviceId, name, url, createdDate, newStatus))
                                sessions.forEach {
                                    it.send(json)
                                }
                            }
                        } catch (e: Exception) {
                            if (status != UNAVAILABLE.name) {
                                updateStatus(serviceId, UNAVAILABLE)
                                val json = Json.encodeToString(Service(serviceId, name, url, createdDate, UNAVAILABLE))
                                sessions.forEach {
                                    it.send(json)
                                }
                            }
                        }
                    }
                }
            }
            delay(intervalSeconds * 1000)
        }
    }
}

private fun updateStatus(serviceId: String, status: ServiceStatus) = transaction {
    Services.update({ Services.id eq serviceId }) {
        it[Services.status] = status.name
    }
}
