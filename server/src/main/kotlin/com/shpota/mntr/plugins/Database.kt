package com.shpota.mntr.plugins

import org.jetbrains.exposed.sql.Database

fun startDatabase() = Database.connect(
    "jdbc:postgresql://localhost:5432/mntr",
    driver = "org.postgresql.Driver",
    user = "mntr",
    password = "mntr"
)
