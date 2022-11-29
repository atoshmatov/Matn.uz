package uz.uicgroup.domain.repository.dictionary

import retrofit2.Response
import uz.uicgroup.data.mapper.DictionaryDto
import uz.uicgroup.data.mapper.WordDto

interface DictionaryRepository {
    suspend fun getDictionariesByQuery(query: String): Response<List<DictionaryDto>>
    suspend fun getWordsById(id: Int): Response<WordDto>
}