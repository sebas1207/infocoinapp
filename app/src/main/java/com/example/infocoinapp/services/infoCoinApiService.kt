package com.example.infocoinapp.services

import com.example.infocoinapp.models.Asset
import com.example.infocoinapp.models.AssetsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class infoCoinApiService  @Inject constructor(
    private val client: HttpClient
){
    suspend fun getAssets(): AssetsResponse {
        val response: HttpResponse = client.get(urlString ="https://rest.coincap.io/v3/assets?apiKey=" )
        return response.body()
    }
}