package com.shpota.mntr.models

import com.shpota.mntr.models.ServiceStatus.OK
import com.shpota.mntr.models.ServiceStatus.FAIL
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

enum class ServiceStatus { OK, FAIL, UNKNOWN }

fun String.asServiceStatus(): ServiceStatus = when (this) {
    OK.name -> OK
    FAIL.name -> FAIL
    else -> UNKNOWN
}
