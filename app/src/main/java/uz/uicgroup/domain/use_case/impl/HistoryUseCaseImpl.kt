package uz.uicgroup.domain.use_case.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.uicgroup.data.common.Resource
import uz.uicgroup.data.local.WordEntity
import uz.uicgroup.data.local.toDictionaryData
import uz.uicgroup.data.local.toWordData
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.domain.model.WordData
import uz.uicgroup.domain.model.toWordEntity
import uz.uicgroup.domain.repository.HistoryRepository
import uz.uicgroup.domain.repository.impl.HistoryRepositoryImpl
import uz.uicgroup.domain.use_case.HistoryUseCase
import javax.inject.Inject


class HistoryUseCaseImpl
@Inject constructor(
    private val history: HistoryRepository
) : HistoryUseCase {
    override fun getHistoryList(): Flow<Resource<List<DictionaryData>>> = flow {
        val historyList = history.getHistory()
        Timber.tag("AAA").d("$historyList")
        emit(Resource.Success(historyList.map { it.toDictionaryData() }))
    }

    override fun getByIdWord(id: Int): Flow<Resource<WordData>> = flow {
        emit(Resource.Success(history.getWords(id).toWordData()))
    }

    override fun addHistory(latin: WordData): Flow<Resource<Unit>> = flow {
        Timber.tag("AAA").d("addWordHistory:UseCae")
        emit(Resource.Success(history.addHistory(latin.toWordEntity())))
    }

    override fun deleteTable(): Flow<Resource<Unit>> = flow {
        emit(Resource.Success(history.deleteTable()))
    }
}