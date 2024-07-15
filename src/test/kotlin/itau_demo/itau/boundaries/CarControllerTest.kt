package itau_demo.itau.boundaries

import itau_demo.itau.domain.Car
import itau_demo.itau.infrastructure.CarRepository
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var carRepository: CarRepository

    @Test
    @Transactional
    fun `test POST cars can create a car and return 201`() {
        val request = """{"brand": "Nissan","model": "March","value": 45000.21,"registrationDate": "2024-01-01"}"""

        mockMvc.perform(
            post("/cars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request)
        ).andExpect(status().isCreated)
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.brand", `is`("Nissan")))
        .andExpect(jsonPath("$.model", `is`("March")))
    }

    @Test
    @Transactional
    fun `test GET cars can list all cars`() {
        val car1 = carRepository.save(Car(
            brand = "Nissan",
            model = "March",
            value = 43432.35,
            registerDate = LocalDate.now()
        ))

        val car2 = carRepository.save(Car(
            brand = "Nissan",
            model = "March",
            value = 43432.35,
            registerDate = LocalDate.now()
        ))

        mockMvc.perform(
            get("/cars")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id", `is`(car1.id.toString())))
            .andExpect(jsonPath("$[1].id", `is`(car2.id.toString())))
    }

    @Test
    @Transactional
    fun `test GET cars will return an empty list if no car is found`() {
        mockMvc.perform(
            get("/cars")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$").isEmpty)
    }
}