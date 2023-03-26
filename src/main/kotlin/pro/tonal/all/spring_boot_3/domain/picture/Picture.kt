package pro.tonal.all.spring_boot_3.domain.picture

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL
import pro.tonal.all.spring_boot_3.domain.BaseEntity

@Entity
class Picture: BaseEntity() {
    @Column(nullable = false)
    @NotBlank(message = "title不允许为空")
    var title:String? = null

    @Column(nullable = false)
    @URL(message = "这里必须为url")
    var url:String? =null

}