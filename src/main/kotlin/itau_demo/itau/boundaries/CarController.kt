package itau_demo.itau.boundaries

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import itau_demo.itau.domain.Car
import itau_demo.itau.infrastructure.CarRepository
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("cars")
class CarController(
    private val repository: CarRepository
) {
    @GetMapping
    fun list(): MutableIterable<Car> {
        return repository.findAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: CreateCarRequest): Car {
        val car = Car(
            brand = request.brand,
            model = request.model,
            value = request.value,
            registerDate = request.registrationDate
        )

        return repository.save(car);
    }
}