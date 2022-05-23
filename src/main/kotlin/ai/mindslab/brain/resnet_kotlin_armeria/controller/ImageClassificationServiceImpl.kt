package ai.mindslab.brain.resnet_kotlin_armeria.controller

import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassification
import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassificationServiceGrpcKt
import ai.mindslab.brain.resnet_kotlin_armeria.classifyReply
import ai.mindslab.brain.resnet_kotlin_armeria.service.ImageClassificationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class ImageClassificationServiceImpl: ImageClassificationServiceGrpcKt.ImageClassificationServiceCoroutineImplBase() {
    val service = ImageClassificationService()
    override suspend fun classify(requests: Flow<ImageClassification.ClassifyRequest>): ImageClassification.ClassifyReply {
        var data = byteArrayOf()
        requests.onEach { request -> data += request.data.toByteArray() }.collect()

        return classifyReply {
            result = service.classify(data)
        }
    }
}