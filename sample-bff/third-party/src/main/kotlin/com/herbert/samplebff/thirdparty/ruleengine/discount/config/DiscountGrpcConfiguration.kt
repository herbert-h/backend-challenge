package com.herbert.samplebff.thirdparty.ruleengine.discount.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "discount.grpc")
data class DiscountGrpcConfiguration(
    val host: String,
    val port: Int,
    val deadlineInMilliseconds: Long
)
