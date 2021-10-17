package com.shpota.mntr.db

import org.jetbrains.exposed.sql.Table

object Services : Table() {
    val id = varchar("id", 255)
    val name = varchar("name", 255)
    val url = text("url")
    val createdDate = long("created_date")
    val status = varchar("status", 20)

    override val primaryKey = PrimaryKey(id)
}
