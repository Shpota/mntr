package com.shpota.mntr.models

import com.shpota.mntr.models.ServiceStatus.AVAILABLE
import com.shpota.mntr.models.ServiceStatus.UNAVAILABLE
import com.shpota.mntr.models.ServiceStatus.UNKNOWN
import kotlinx.serialization.Serializable
import java.util.UUID.randomUUID

@Serializable
data class Service(
    val id: String = randomUUID().toString(),
    val name: String,
    val url: String,
    val createdDate: Long,
    val status: ServiceStatus,
)

@Serializable
data class CreateServiceRequest(val name: String, val url: String)

enum class ServiceStatus { AVAILABLE, UNAVAILABLE, UNKNOWN }

fun String.asServiceStatus(): ServiceStatus = when (this) {
    AVAILABLE.name -> AVAILABLE
    UNAVAILABLE.name -> UNAVAILABLE
    else -> UNKNOWN
}
