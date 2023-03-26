package pro.tonal.all.spring_boot_3.domain.picture

import org.springframework.data.repository.CrudRepository

interface PictureRepository: CrudRepository<Picture,Int> {
    override fun findAll(): List<Picture>
    fun findByTitle(title:String):Picture
}