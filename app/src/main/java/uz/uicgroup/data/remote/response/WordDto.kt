package uz.uicgroup.data.remote.response

import uz.uicgroup.domain.model.WordData

data class WordDto(
    val definitions: Any,
    val id: Int,
    val meanings: List<Any>,
    val source: Any,
    val syllable: String,
    val type: Any,
    val word: String
)

fun WordDto.toWordsData(): WordData {
    return WordData(
        id = id,
        word = word,
        syllable = syllable,
        meanings = meanings
    )
}