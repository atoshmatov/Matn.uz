package uz.uicgroup.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import uz.uicgroup.data.local.WordEntity
import uz.uicgroup.di.databsemodule.TableName
import uz.uicgroup.domain.model.WordData

@Dao
interface DictionaryDao : BaseDao<WordEntity> {
    @Query("SELECT * FROM word_entity")
    fun getHistory(): List<WordEntity>

    @Query("SELECT * FROM word_entity WHERE id=:id")
    fun getByIdWord(id: Int): WordEntity

    @Query("DELETE FROM word_entity ")
    fun delete()
}