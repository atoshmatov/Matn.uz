package uz.uicgroup.data.remote.response

import uz.uicgroup.domain.model.SuggestionsData

data class SuggestionsDto(
    val errors: Boolean,
    val data: List<SuggesData>
)

fun SuggestionsDto?.toSuggestion(): SuggestionsData {
    return SuggestionsData(
        data = this?.data ?: emptyList()
    )
}
