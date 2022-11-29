package uz.uicgroup.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.uicgroup.data.remote.models.LatinRequest

interface TranslationApi {

    @POST("/api/v1/cyrillic/")
    suspend fun editCyrillicText(
        @Body text: LatinRequest
    ): Response<String>

    @POST("/api/v1/latin/")
    suspend fun editLatinText(
        @Body text: LatinRequest
    ): Response<String>
}