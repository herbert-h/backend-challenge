package com.herbert.samplebff.thirdparty.helloworld.config

import io.grpc.examples.helloworld.GreeterGrpcKt

data class HelloWorldGrpcClient(
    val stub: GreeterGrpcKt.GreeterCoroutineStub,
    val deadlineInMilliseconds: Long
)
