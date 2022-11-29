package uz.uicgroup.domain.repository.trans

import retrofit2.Response

interface TranslationRepository {
    suspend fun translateToLatin(text: String): Response<String>
    suspend fun translateToCyrillic(text: String): Response<String>
}