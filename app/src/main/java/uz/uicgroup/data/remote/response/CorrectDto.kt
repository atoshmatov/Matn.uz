package uz.uicgroup.data.remote.response

import uz.uicgroup.domain.model.CorrectData

data class CorrectDto(
    val errors: Boolean,
    val data: List<String>
)

fun CorrectDto?.toCorrect(): CorrectData {
    return CorrectData(
        data = this?.data ?: emptyList()
    )
}
