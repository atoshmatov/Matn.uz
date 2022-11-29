package uz.uicgroup.domain.repository.dictionary

import retrofit2.Response
import uz.uicgroup.data.mapper.DictionaryDto
import uz.uicgroup.data.mapper.WordDto
import uz.uicgroup.data.remote.api.DictionaryApi
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi
) : DictionaryRepository {
    override suspend fun getDictionariesByQuery(query: String): Response<List<DictionaryDto>> =
        dictionaryApi.getSearchText(query)

    override suspend fun getWordsById(id: Int): Response<WordDto> =
        dictionaryApi.getSearchWord(id)
}
