package uz.uicgroup.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.domain.model.WordData

private const val WORD_ENTITY = "word_entity"

@Entity(tableName = WORD_ENTITY)
data class WordEntity(
    val id: Int,
    @PrimaryKey
    val word: String,
    val syllable: String
)

fun WordEntity?.toWordData(): WordData {
    return WordData(
        id = this?.id ?: 0,
        word = this?.word ?: "",
        syllable = this?.syllable ?: ""
    )
}

fun WordEntity?.toDictionaryData(): DictionaryData {
    return DictionaryData(
        id = this?.id ?: 0,
        latin = this?.word ?: "",
    )
}