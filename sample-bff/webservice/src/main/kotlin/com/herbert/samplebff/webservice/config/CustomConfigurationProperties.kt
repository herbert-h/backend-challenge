package com.herbert.samplebff.webservice.config

import com.herbert.samplebff.thirdparty.ruleengine.discount.config.DiscountGrpcConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    value = [
        DiscountGrpcConfiguration::class
    ]
)
class CustomConfigurationProperties
