package uz.uicgroup.data.remote.response

import uz.uicgroup.domain.model.DictionaryData

data class DictionaryDto(
    val id: Int,
    val latin: String
)

fun DictionaryDto?.toDicData(): DictionaryData {
    return DictionaryData(
        id = this?.id ?: 0,
        latin = this?.latin ?: ""
    )
}