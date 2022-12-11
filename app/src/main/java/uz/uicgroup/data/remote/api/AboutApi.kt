package uz.uicgroup.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.uicgroup.data.remote.models.AboutResponse

interface AboutApi {
    @GET("/api/v1/collaborators")
    suspend fun getAboutList(): Response<AboutResponse>
}