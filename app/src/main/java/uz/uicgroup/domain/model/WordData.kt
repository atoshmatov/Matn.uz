package uz.uicgroup.domain.model

import uz.uicgroup.data.local.WordEntity

data class WordData(
    val id: Int,
    val word: String,
    val syllable: String,
)

fun WordData?.toWordEntity(): WordEntity {
    return WordEntity(
        id = this?.id ?: 0,
        word = this?.word ?: "",
        syllable = this?.syllable ?: ""
    )
}