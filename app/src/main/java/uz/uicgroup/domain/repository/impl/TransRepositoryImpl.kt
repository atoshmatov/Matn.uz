package uz.uicgroup.domain.repository.impl

import retrofit2.Response
import uz.uicgroup.data.remote.api.TransApi
import uz.uicgroup.data.remote.request.LatinRequest
import uz.uicgroup.domain.repository.TransRepository
import javax.inject.Inject

class TransRepositoryImpl @Inject constructor(
    private val transApi: TransApi
) : TransRepository {

    override suspend fun transLatin(text: String): Response<String> =
        transApi.editLatinText(LatinRequest(text))

    override suspend fun transCyrillic(text: String): Response<String> =
        transApi.editCyrillicText(LatinRequest(text))
}