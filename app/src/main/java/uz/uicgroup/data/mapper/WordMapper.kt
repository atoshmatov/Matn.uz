package uz.uicgroup.data.mapper

import uz.uicgroup.data.local.WordEntity
import uz.uicgroup.domain.models.DictionaryData
import uz.uicgroup.domain.models.WordData

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

fun WordData?.toWordEntity(): WordEntity {
    return WordEntity(
        id = this?.id ?: 0,
        word = this?.word ?: "",
        syllable = this?.syllable ?: ""
    )
}
