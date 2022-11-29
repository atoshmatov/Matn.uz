package uz.uicgroup.domain.repository.impl

import retrofit2.Response
import uz.uicgroup.data.remote.api.DictionaryApi
import uz.uicgroup.data.remote.response.DictionaryDto
import uz.uicgroup.data.remote.response.WordDto
import uz.uicgroup.domain.model.WordData
import uz.uicgroup.domain.repository.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi
) : DictionaryRepository {
    override suspend fun getSearch(search: String): Response<List<DictionaryDto>> =
        dictionaryApi.getSearchText(search)

    override suspend fun getWords(id: Int): Response<WordDto> =
        dictionaryApi.getSearchWord(id)
}
