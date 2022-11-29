package uz.uicgroup.data.mapper

import uz.uicgroup.data.remote.models.SuggesData
import uz.uicgroup.domain.models.SuggestionsData

data class SuggestionsDto(
    val errors: Boolean,
    val data: List<SuggesData>
)

fun SuggestionsDto?.toSuggestion(): SuggestionsData {
    return SuggestionsData(
        data = this?.data ?: emptyList()
    )
}
