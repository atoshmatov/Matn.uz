package uz.uicgroup.data.mapper

import uz.uicgroup.domain.models.CorrectData

data class CorrectDto(
    val errors: Boolean,
    val data: List<String>
)

fun CorrectDto?.toCorrect(): CorrectData {
    return CorrectData(
        data = this?.data ?: emptyList()
    )
}
