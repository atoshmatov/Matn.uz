package uz.uicgroup.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import uz.uicgroup.data.local.WordEntity

@Dao
interface DictionaryDao : BaseDao<WordEntity> {
    @Query("SELECT * FROM word_entity")
    fun getHistory(): List<WordEntity>

    @Query("SELECT * FROM word_entity WHERE id=:id")
    fun getByIdWord(id: Int): WordEntity

    @Query("DELETE FROM word_entity ")
    fun delete()
}