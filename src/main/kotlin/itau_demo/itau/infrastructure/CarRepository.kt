package itau_demo.itau.infrastructure

import itau_demo.itau.domain.Car
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CarRepository : CrudRepository<Car, UUID>