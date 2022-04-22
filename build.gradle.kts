import com.google.protobuf.gradle.*
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

    protobuf(files("${project.path}/proto"))

    implementation("io.grpc:grpc-kotlin-stub:1.2.1")
    implementation("io.grpc:grpc-protobuf:1.45.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.20.0")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.20.0"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.45.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.2.1:jdk7@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}