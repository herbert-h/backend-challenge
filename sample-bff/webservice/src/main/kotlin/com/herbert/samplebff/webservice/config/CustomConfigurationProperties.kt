package com.herbert.samplebff.webservice.config

import com.herbert.samplebff.thirdparty.discount.config.DiscountGrpcConfiguration
import com.herbert.samplebff.thirdparty.helloworld.config.HelloWorldGrpcConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(
    value = [
        HelloWorldGrpcConfiguration::class,
        DiscountGrpcConfiguration::class
    ]
)
class CustomConfigurationProperties
