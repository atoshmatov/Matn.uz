package uz.uicgroup.domain.use_case.trans

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.utils.common.Resource

interface TransUseCase {
    fun getLatin(text: String): Flow<Resource<String>>
    fun getCyrille(text: String): Flow<Resource<String>>
}