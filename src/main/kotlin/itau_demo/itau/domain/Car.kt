package itau_demo.itau.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.UUID

//Atributos: ID (gerado automaticamente), Marca, Modelo, Valor, data de cadastro

@Entity
data class Car(
    @Id
    val id: UUID = UUID.randomUUID(),
    val brand: String,
    val model: String,
    @Column(name = "car_value")
    val value: Double,
    val registerDate: LocalDate
)