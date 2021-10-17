package com.shpota.mntr.models

import com.shpota.mntr.models.ServiceStatus.AVAILABLE
import com.shpota.mntr.models.ServiceStatus.UNAVAILABLE
import com.shpota.mntr.models.ServiceStatus.UNKNOWN
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID.randomUUID

@Serializable
data class Service(
    val id: String = randomUUID().toString(),
    val name: String,
    val url: String,
    val createdDate: Long = Instant.now().toEpochMilli(),
    val status: ServiceStatus = UNKNOWN,
)

@Serializable
data class CreateServiceRequest(val name: String, val url: String)

@Serializable
enum class ServiceStatus {
    @SerialName("AVAILABLE")
    AVAILABLE,
    @SerialName("UNAVAILABLE")
    UNAVAILABLE,
    @SerialName("UNKNOWN")
    UNKNOWN
}

fun String.asServiceStatus(): ServiceStatus = when (this) {
    AVAILABLE.name -> AVAILABLE
    UNAVAILABLE.name -> UNAVAILABLE
    else -> UNKNOWN
}
