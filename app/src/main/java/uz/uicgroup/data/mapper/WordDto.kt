package uz.uicgroup.data.mapper

import uz.uicgroup.domain.models.WordData

data class WordDto(
    val definitions: Any,
    val id: Int,
    val meanings: List<Any>,
    val source: Any,
    val syllable: String,
    val type: Any,
    val word: String
)

fun WordDto?.toWordsData(): WordData {
    return WordData(
        id = this?.id ?: 0,
        word = this?.word ?: "",
        syllable = this?.syllable ?: ""
    )
}