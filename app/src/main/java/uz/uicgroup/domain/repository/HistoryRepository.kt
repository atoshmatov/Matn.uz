package uz.uicgroup.domain.repository

import uz.uicgroup.data.local.WordEntity
import uz.uicgroup.domain.model.WordData

interface HistoryRepository {
    suspend fun getHistory(): List<WordEntity>
    suspend fun addHistory(latin: WordEntity)
    suspend fun getWords(id: Int): WordEntity
    suspend fun deleteTable()
}