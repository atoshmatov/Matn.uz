package uz.uicgroup.domain.repository

import retrofit2.Response
import uz.uicgroup.data.remote.response.DictionaryDto
interface DictionaryRepository {
    suspend fun getSearch(search:String):Response<List<DictionaryDto>>
}