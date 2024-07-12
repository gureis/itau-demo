package itau_demo.itau

import itau_demo.itau.boundaries.CarController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ConfigurationPropertiesScan
class ItauDemoApplication

fun main(args: Array<String>) {
	runApplication<ItauDemoApplication>(*args)
}
