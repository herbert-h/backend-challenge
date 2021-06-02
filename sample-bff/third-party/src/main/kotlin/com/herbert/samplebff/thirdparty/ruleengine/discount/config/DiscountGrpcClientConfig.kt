package com.herbert.samplebff.thirdparty.ruleengine.discount.config

import com.herbert.samplebff.thirdparty.ruleengine.discount.DiscountRuleEngineGrpcKt
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscountGrpcClientConfig(
    private val configuration: DiscountGrpcConfiguration
) {
    @Bean
    fun discountGrpcClient() = DiscountGrpcClient(
        stub = discountGrpcStub(),
        deadlineInMilliseconds = configuration.deadlineInMilliseconds
    )

    fun discountGrpcStub(): DiscountRuleEngineGrpcKt.DiscountRuleEngineCoroutineStub {
        val channel = ManagedChannelBuilder
            .forAddress(configuration.host, configuration.port)
            .usePlaintext()
            .build()

        return DiscountRuleEngineGrpcKt.DiscountRuleEngineCoroutineStub(channel)
    }
}
