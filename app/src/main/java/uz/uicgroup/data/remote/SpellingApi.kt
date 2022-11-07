package uz.uicgroup.data.remote

import retrofit2.http.POST
import uz.uicgroup.data.remote.apiModel.Correct
import uz.uicgroup.data.remote.apiModel.Suggestions

interface SpellingApi {

    @POST("/v1/correct")
    suspend fun correctText(text: List<String>): Correct

    @POST("/v1/suggestions")
    suspend fun suggestionsText(text: List<String>): Suggestions
}