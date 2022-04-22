package ai.mindslab.brain.resnet_kotlin_armeria.controller

import ai.mindslab.brain.resnet_kotlin_armeria.Test
import ai.mindslab.brain.resnet_kotlin_armeria.TestServiceGrpcKt
import ai.mindslab.brain.resnet_kotlin_armeria.testReply

class TestServiceImpl: TestServiceGrpcKt.TestServiceCoroutineImplBase() {
    override suspend fun test(request: Test.TestRequest): Test.TestReply {
        return testReply {
            reply = "Hi"
        }
    }
}