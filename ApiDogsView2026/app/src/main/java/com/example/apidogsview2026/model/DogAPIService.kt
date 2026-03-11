package com.example.apidogsview2026.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPIService {
    @GET("{raza}/images")
    suspend fun getFotosPerros(@Path("raza") raza: String): Response<DogRespuesta>
}