package ai.mindslab.brain.resnet_kotlin_armeria.controller

import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassification
import ai.mindslab.brain.resnet_kotlin_armeria.ImageClassificationServiceGrpcKt
import ai.mindslab.brain.resnet_kotlin_armeria.classifyReply
import ai.mindslab.brain.resnet_kotlin_armeria.service.ImageClassificationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.slf4j.LoggerFactory

class ImageClassificationServiceImpl: ImageClassificationServiceGrpcKt.ImageClassificationServiceCoroutineImplBase() {
    val logger = LoggerFactory.getLogger(ImageClassificationServiceImpl::class.qualifiedName)
    val service = ImageClassificationService()
    override suspend fun classify(requests: Flow<ImageClassification.ClassifyRequest>): ImageClassification.ClassifyReply {
        var data = byteArrayOf()
        requests.onEach { request -> data += request.data.toByteArray() }.collect()

        logger.debug(data.toString())

        return classifyReply {
            result = service.classify(data)
        }
    }
}