package com.shpota.mntr.plugins

import org.jetbrains.exposed.sql.Database

fun startDatabase(): Database {
    val host = System.getenv("DB_HOST") ?: "localhost"
    val user = System.getenv("DB_USER") ?: "mntr"
    val password = System.getenv("DB_PASSWORD") ?: "mntr"

    return Database.connect(
        "jdbc:postgresql://$host:5432/mntr",
        driver = "org.postgresql.Driver",
        user = user,
        password = password
    )
}
