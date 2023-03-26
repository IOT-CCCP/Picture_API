package pro.tonal.all.spring_boot_3.domain.picture

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.validation.constraints.NotEmpty
import pro.tonal.all.spring_boot_3.domain.BaseEntity

@Entity
class Picture: BaseEntity() {
    @Column(nullable = false)
    @NotEmpty(message = "title不允许为空")
    var title:String? = null

    @Column(nullable = false)
    @NotEmpty(message = "url不允许为空")
    var url:String? =null

}