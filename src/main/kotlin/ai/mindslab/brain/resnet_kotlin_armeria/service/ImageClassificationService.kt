package ai.mindslab.brain.resnet_kotlin_armeria.service

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtException
import ai.onnxruntime.OrtUtil
import com.linecorp.armeria.common.grpc.StatusCauseException
import com.linecorp.armeria.common.grpc.ThrowableProto
import org.slf4j.LoggerFactory

class ImageClassificationService {
    private val logger = LoggerFactory.getLogger(ImageClassificationService::class.qualifiedName)

    private val env = OrtEnvironment.getEnvironment()
    private val session = env.createSession("src/main/resources/resnet50.onnx")
    fun classify(imageTensor: FloatArray, shape: LongArray): FloatArray {
        val reshapedImageTensor = OrtUtil.reshape(imageTensor, shape)
        val imageOnnxTensor = OnnxTensor.createTensor(env, reshapedImageTensor)
        val inputs = mapOf("actual_input" to imageOnnxTensor)

        return try {
            val results = session.run(inputs)
            val resultTensor: OnnxTensor = results[0] as OnnxTensor

            val resultBuffer = resultTensor.floatBuffer
            resultBuffer.array()
        } catch (e: OrtException) {
            logger.error(e.message)
            throw StatusCauseException(ThrowableProto.getDefaultInstance())
        }
    }
}