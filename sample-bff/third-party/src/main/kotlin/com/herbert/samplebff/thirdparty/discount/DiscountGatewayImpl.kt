package com.herbert.samplebff.thirdparty.discount

import com.herbert.samplebff.business.product.DiscountGateway
import com.herbert.samplebff.thirdparty.discount.config.DiscountGrpcClient
import java.util.concurrent.TimeUnit
import org.springframework.stereotype.Component

@Component
class DiscountGatewayImpl(
    private val client: DiscountGrpcClient
): DiscountGateway {

    override suspend fun calculateProductDiscountPercentage(productId: String, userId: String?): Float {
        val request = DiscountRequest.newBuilder()
            .setProductId(productId)
            .setUserId(userId ?: "")
            .build()

        return client.stub
            .withDeadlineAfter(client.deadlineInMilliseconds, TimeUnit.MILLISECONDS)
            .calculateDiscount(request)
            .percentage
    }

}
