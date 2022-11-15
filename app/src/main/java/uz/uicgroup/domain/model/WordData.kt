package uz.uicgroup.domain.model

data class WordData(
    val id: Int,
    val word: String,
    val syllable: String,
    val meanings:List<Any>
)