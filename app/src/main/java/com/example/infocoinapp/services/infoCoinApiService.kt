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
        val response: HttpResponse = client.get(urlString ="https://rest.coincap.io/v3/assets?apiKey=9a58fc2b8c09ad09b4fd7d26547ee44a237f8f8f9cb40faee2aac4258c9a111a" )
        return response.body()
    }
}