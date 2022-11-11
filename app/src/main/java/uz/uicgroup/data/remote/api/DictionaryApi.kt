package uz.uicgroup.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.uicgroup.data.remote.response.DictionaryResponse
import uz.uicgroup.data.remote.response.WordResponse

interface DictionaryApi {

    @GET("/api/v1/dictionary")
    fun getSearchText(@Query("search") search: String):
            Response<List<DictionaryResponse>>

    @GET("/api/v1/words")
    fun getSearchWord(@Path("id") id: Int):
            Response<List<WordResponse>>
}