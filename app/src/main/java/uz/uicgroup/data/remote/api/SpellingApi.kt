package uz.uicgroup.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.uicgroup.data.mapper.CorrectDto
import uz.uicgroup.data.mapper.SuggestionsDto
import uz.uicgroup.data.remote.models.TextList

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