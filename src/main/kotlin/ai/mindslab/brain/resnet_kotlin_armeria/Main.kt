package ai.mindslab.brain.resnet_kotlin_armeria

import com.linecorp.armeria.server.Server

fun main() {
    val server: Server? = Server.builder().apply {}.build()

    server?.start()
}