package uz.uicgroup.domain.repository.impl

import timber.log.Timber
import uz.uicgroup.data.local.WordEntity
import uz.uicgroup.data.local.dao.DictionaryDao
import uz.uicgroup.domain.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl
@Inject constructor(
    private val historyDao: DictionaryDao
) : HistoryRepository {
    override suspend fun getHistory(): List<WordEntity> =
        historyDao.getHistory()

    override suspend fun addHistory(latin: WordEntity) {
        Timber.tag("AAA").d("addHistory:Repository")
        historyDao.insert(latin)
    }

    override suspend fun getWords(id: Int): WordEntity =
        historyDao.getByIdWord(id)

    override suspend fun deleteTable() {
        historyDao.delete()
    }
}