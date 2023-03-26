package pro.tonal.all.spring_boot_3.applicaition

import jakarta.inject.Named
import jakarta.transaction.Transactional
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import pro.tonal.all.spring_boot_3.domain.picture.Picture
import pro.tonal.all.spring_boot_3.domain.picture.PictureService
import java.io.InputStream
import java.util.*

@Named
@Transactional
class PictureApplicationService(private val pictureService: PictureService) {
    fun createPicture(title:String,stream: InputStream,fileDetail: FormDataContentDisposition): Picture {
        return pictureService.createPicture(title,stream, fileDetail)
    }
    fun getAllPicture(): List<Picture> {
        return pictureService.getAllPicture()
    }
    fun findPictureById(@Valid @NotNull(message = "id不能为空") id:Int?): Optional<Picture> {
        return pictureService.findPictureById(id!!)
    }
    fun updatePicture(picture: Picture){
        picture.id?:throw ConstraintViolationException("id不能为空",null)
        pictureService.updatePicture(picture)
    }
    fun deletePicture(@Valid @NotNull(message = "id不能为空") id:Int?){
        pictureService.deletePicture(id!!)
    }

}