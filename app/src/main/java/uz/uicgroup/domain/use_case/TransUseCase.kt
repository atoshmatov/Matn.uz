package uz.uicgroup.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.data.remote.request.LatinRequest

interface TransUseCase {
    fun getLatin(text: LatinRequest): Flow<Result<String>>
    fun getCyrille(text: LatinRequest): Flow<Result<String>>
}