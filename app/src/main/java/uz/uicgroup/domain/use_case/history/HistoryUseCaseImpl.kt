package uz.uicgroup.domain.use_case.history

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import uz.uicgroup.data.mapper.toDictionaryData
import uz.uicgroup.data.mapper.toWordData
import uz.uicgroup.data.mapper.toWordEntity
import uz.uicgroup.domain.models.DictionaryData
import uz.uicgroup.domain.models.WordData
import uz.uicgroup.domain.repository.history.HistoryRepository
import uz.uicgroup.utils.common.Resource
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
        emit(Resource.Success(history.getWordsById(id).toWordData()))
    }

    override fun addHistory(latin: WordData): Flow<Resource<Unit>> = flow {
        Timber.tag("AAA").d("addWordHistory:UseCae")
        emit(Resource.Success(history.addHistory(latin.toWordEntity())))
    }

    override fun deleteTable(): Flow<Resource<Unit>> = flow {
        emit(Resource.Success(history.deleteTable()))
    }
}