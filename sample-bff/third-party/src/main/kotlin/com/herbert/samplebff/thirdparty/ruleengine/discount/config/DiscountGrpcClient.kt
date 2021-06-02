package com.herbert.samplebff.thirdparty.ruleengine.discount.config

import com.herbert.samplebff.thirdparty.ruleengine.discount.DiscountRuleEngineGrpcKt

data class DiscountGrpcClient(
    val stub: DiscountRuleEngineGrpcKt.DiscountRuleEngineCoroutineStub,
    val deadlineInMilliseconds: Long
)
