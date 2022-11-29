package uz.uicgroup.data.mapper

import uz.uicgroup.domain.models.DictionaryData

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