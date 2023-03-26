package pro.tonal.all.spring_boot_3.domain

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.io.Serializable


@MappedSuperclass
open class BaseEntity:Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
}