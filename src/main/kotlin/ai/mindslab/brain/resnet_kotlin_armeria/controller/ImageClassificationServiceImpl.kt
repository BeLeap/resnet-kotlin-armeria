package ai.mindslab.brain.resnet_kotlin_armeria.controller

import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassification
import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassificationServiceGrpcKt
import ai.mindslab.brain.resnet_kotlin_armeria.classifyReply
import ai.mindslab.brain.resnet_kotlin_armeria.service.ImageClassificationService

class ImageClassificationServiceImpl: ImageClassificationServiceGrpcKt.ImageClassificationServiceCoroutineImplBase() {
    val service = ImageClassificationService()
    override suspend fun classify(request: ImageClassification.ClassifyRequest): ImageClassification.ClassifyReply {
        val data = request.dataList.toFloatArray()
        val shape = request.shapeList.toLongArray()

        return classifyReply {
            result += service.classify(data, shape).toList()
        }
    }
}