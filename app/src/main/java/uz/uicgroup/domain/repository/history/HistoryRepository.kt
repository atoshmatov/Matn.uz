package uz.uicgroup.domain.repository.history

import uz.uicgroup.data.local.WordEntity

interface HistoryRepository {
    suspend fun getHistory(): List<WordEntity>
    suspend fun addHistory(latin: WordEntity)
    suspend fun getWordsById(id: Int): WordEntity
    suspend fun deleteTable()
}