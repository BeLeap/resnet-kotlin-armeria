# ResNet Kotlin Armeria

This repository contains POC of running [onnx](https://onnxruntime.ai) exported ResNet on [gRPC](https://grpc.io) server built with [Armeria](https://armeria.dev) using [Kotlin](https://kotlinlang.org/).

## Running Demonstration

https://user-images.githubusercontent.com/46488521/170187482-4e045bae-6749-49e4-8c90-d916177f19b4.mp4

## Steps

1. Export ResNet model
    * [export-model script](./export-model.py) exports ResNet model
2. Write service
    * ref. [ONNXruntime Java Getting Started](https://onnxruntime.ai/docs/get-started/with-java.html)
    * [Service](./src/main/kotlin/ai/mindslab/brain/resnet_kotlin_armeria/service/ImageClassificationService.kt)
3. Write proto
    * [Proto](./src/main/proto/image-classification.proto)
4. Write server(including controller)
    * ref. [Armeria gRPC Service Tutorial](https://armeria.dev/docs/server-grpc)
    * [Main](./src/main/kotlin/ai/mindslab/brain/resnet_kotlin_armeria/Main.kt) & [Controller](./src/main/kotlin/ai/mindslab/brain/resnet_kotlin_armeria/controller/ImageClassificationServiceImpl.kt)