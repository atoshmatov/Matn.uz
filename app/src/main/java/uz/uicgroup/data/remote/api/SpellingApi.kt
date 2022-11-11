package uz.uicgroup.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.uicgroup.data.remote.request.TextList
import uz.uicgroup.data.remote.response.CorrectDto
import uz.uicgroup.data.remote.response.SuggestionsDto

interface SpellingApi {

    @POST("/api/v1/correct")
    suspend fun correctText(
        @Body text: TextList
    ): Response<CorrectDto>

    @POST("/api/v1/suggestions")
    suspend fun suggestionsText(
        @Body text: TextList
    ): Response<SuggestionsDto>
}