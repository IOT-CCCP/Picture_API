package pro.tonal.all.spring_boot_3.applicaition

import jakarta.inject.Named
import jakarta.transaction.Transactional
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import pro.tonal.all.spring_boot_3.domain.picture.Picture
import pro.tonal.all.spring_boot_3.domain.picture.PictureService
import java.io.InputStream

@Named
@Transactional
class PictureApplicationService(private val pictureService: PictureService) {
    fun createPicture(title:String,stream: InputStream,fileDetail: FormDataContentDisposition): Picture {
        return pictureService.createPicture(title,stream, fileDetail)
    }
    fun getAllPicture(): List<Picture> {
        return pictureService.getAllPicture()
    }

}