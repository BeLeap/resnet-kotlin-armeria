import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.18")
    }
}

plugins {
    kotlin("jvm") version "1.6.20"
    id("java")
    id("idea")
    id("com.google.protobuf") version "0.8.18"
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