package uz.uicgroup.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.uicgroup.data.mapper.DictionaryDto
import uz.uicgroup.data.mapper.WordDto

interface DictionaryApi {

    @GET("/api/v1/dictionary")
    suspend fun getSearchText(
        @Query("search") search: String
    ): Response<List<DictionaryDto>>

    @GET("/api/v1/words/{id}")
    suspend fun getSearchWord(
        @Path("id") id: Int
    ): Response<WordDto>
}