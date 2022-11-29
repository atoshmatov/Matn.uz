package uz.uicgroup.domain.repository

import retrofit2.Response
import uz.uicgroup.data.remote.response.DictionaryDto
import uz.uicgroup.data.remote.response.WordDto
import uz.uicgroup.domain.model.WordData

interface DictionaryRepository {
    suspend fun getSearch(search: String): Response<List<DictionaryDto>>
    suspend fun getWords(id: Int): Response<WordDto>
}