package com.herbert.samplebff.business.helloworld

interface HelloWorldGateway {
    suspend fun hello(name: String): String
}