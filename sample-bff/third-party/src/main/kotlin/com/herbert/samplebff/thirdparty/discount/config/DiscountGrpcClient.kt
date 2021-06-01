package com.herbert.samplebff.thirdparty.discount.config

import com.herbert.samplebff.thirdparty.discount.DiscountRuleEngineGrpcKt

data class DiscountGrpcClient(
    val stub: DiscountRuleEngineGrpcKt.DiscountRuleEngineCoroutineStub,
    val deadlineInMilliseconds: Long
)
