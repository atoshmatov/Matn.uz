package uz.uicgroup.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

private const val WORD_ENTITY = "word_entity"

@Entity(tableName = WORD_ENTITY)
data class WordEntity(
    val id: Int,
    @PrimaryKey
    val word: String,
    val syllable: String
)
