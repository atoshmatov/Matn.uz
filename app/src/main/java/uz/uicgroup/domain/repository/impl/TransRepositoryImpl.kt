package uz.uicgroup.domain.repository.impl

import retrofit2.Response
import uz.uicgroup.BuildConfig.Bearer
import uz.uicgroup.data.remote.TransApi
import uz.uicgroup.data.remote.request.LatinRequest
import uz.uicgroup.domain.repository.TransRepository
import javax.inject.Inject

class TransRepositoryImpl @Inject constructor(
    private val transApi: TransApi
) : TransRepository {

    override suspend fun transLatin(text: LatinRequest): Response<String> =
        transApi.editLatinText(text,"Bearer $Bearer")

    override suspend fun transCyrillic(text: LatinRequest): Response<String> =
        transApi.editCyrillicText(text,"Bearer $Bearer")
}