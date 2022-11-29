package uz.uicgroup.domain.repository.history

import uz.uicgroup.data.local.WordEntity
import uz.uicgroup.data.local.dao.DictionaryDao
import javax.inject.Inject

class HistoryRepositoryImpl
@Inject constructor(
    private val historyDao: DictionaryDao
) : HistoryRepository {
    override suspend fun getHistory(): List<WordEntity> = historyDao.getHistory()

    override suspend fun addHistory(latin: WordEntity) = historyDao.insert(latin)

    override suspend fun getWordsById(id: Int): WordEntity = historyDao.getByIdWord(id)

    override suspend fun deleteTable() = historyDao.delete()
}