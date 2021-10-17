val ktor_version: String by project
val kotlin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "com.shpota.mntr"
version = "0.0.1"
application {
    mainClass.set("com.shpota.mntr.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-websockets:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("org.jetbrains.exposed:exposed-core:0.35.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.35.2")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    runtimeOnly("org.postgresql:postgresql:42.2.24")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}
