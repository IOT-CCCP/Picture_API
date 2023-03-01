package pro.tonal.all.spring_boot_3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBoot3Application

fun main(args: Array<String>) {
    runApplication<SpringBoot3Application>(*args)
}
