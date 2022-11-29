package uz.uicgroup.domain.repository.trans

import retrofit2.Response
import uz.uicgroup.data.remote.api.TranslationApi
import uz.uicgroup.data.remote.models.LatinRequest
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val translationApi: TranslationApi
) : TranslationRepository {

    override suspend fun translateToLatin(text: String): Response<String> =
        translationApi.editLatinText(LatinRequest(text))

    override suspend fun translateToCyrillic(text: String): Response<String> =
        translationApi.editCyrillicText(LatinRequest(text))
}