package uz.uicgroup.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.uicgroup.data.remote.request.LatinRequest

interface TransApi {

    @POST("/api/v1/cyrillic/")
    suspend fun editCyrillicText(
        @Body text: LatinRequest,
        @Header("Authorization") token: String
    ): Response<String>

    @POST("/api/v1/latin/")
    suspend fun editLatinText(
        @Body text: LatinRequest,
        @Header("Authorization") token: String
    ): Response<String>

}