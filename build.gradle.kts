import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    id("java")
    id("idea")
}

group = "ai.mindslab.brain"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation(platform("com.linecorp.armeria:armeria-bom:1.16.0"))
    listOf(
        "armeria",
        "armeria-grpc",
        "armeria-kotlin",
        "armeria-logback",
        "armeria-protobuf",
    ).forEach {
        implementation("com.linecorp.armeria:${it}")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}