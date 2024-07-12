package itau_demo.itau.boundaries

import java.time.LocalDate

data class CreateCarRequest(
    val brand: String,
    val model: String,
    val value: Double,
    val registrationDate: LocalDate
)
