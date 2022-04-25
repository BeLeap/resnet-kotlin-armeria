package ai.mindslab.brain.resnet_kotlin_armeria.controller

import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassification
import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassificationServiceGrpcKt
import ai.mindslab.brain.resnet_kotlin_armeria.classifyReply
import kotlinx.coroutines.flow.Flow

class ImageClassificationServiceImpl: ImageClassificationServiceGrpcKt.ImageClassificationServiceCoroutineImplBase() {
    override suspend fun classify(requests: Flow<ImageClassification.ClassifyRequest>): ImageClassification.ClassifyReply {
        return classifyReply {
            result = "Hi"
        }
    }
}