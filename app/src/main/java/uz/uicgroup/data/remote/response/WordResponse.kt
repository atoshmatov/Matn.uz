package uz.uicgroup.data.remote.response

data class WordResponse(
    val definitions: Any,
    val id: Int,
    val meanings: List<Any>,
    val source: Any,
    val syllable: String,
    val type: Any,
    val word: String
)