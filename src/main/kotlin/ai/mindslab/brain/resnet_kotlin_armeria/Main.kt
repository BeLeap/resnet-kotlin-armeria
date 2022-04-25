package ai.mindslab.brain.resnet_kotlin_armeria

import ai.mindslab.brain.resnet_kotlin_armeria.controller.ImageClassificationServiceImpl
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.grpc.GrpcService
import io.grpc.protobuf.services.ProtoReflectionService

fun main() {
    val server: Server? = Server.builder().apply {
        service(GrpcService.builder().apply {
            addService(ImageClassificationServiceImpl())
            addService(ProtoReflectionService.newInstance())
            supportedSerializationFormats(GrpcSerializationFormats.values())
            enableUnframedRequests(true)
        }.build())
    }.build()

    server?.start()
}