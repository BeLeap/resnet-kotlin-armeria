package ai.mindslab.brain.resnet_kotlin_armeria.service

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtException
import org.slf4j.LoggerFactory

class ImageClassificationService {
    private val logger = LoggerFactory.getLogger(ImageClassificationService::class.qualifiedName)

    private val env = OrtEnvironment.getEnvironment()
    private val session = env.createSession("src/main/resources/resnet50.onnx")
    fun classify(image: ByteArray): String {
        val imageTensor = OnnxTensor.createTensor(env, image)
        val inputs = mapOf("actual_input" to imageTensor)

        try {
            val results = session.run(inputs)
            logger.info(results.toString())
        } catch (e: OrtException) {
            logger.error(e.message)
        }

        return "Hi"
    }
}