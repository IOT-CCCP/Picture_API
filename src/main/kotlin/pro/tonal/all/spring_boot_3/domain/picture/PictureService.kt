package pro.tonal.all.spring_boot_3.domain.picture


import jakarta.inject.Named
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import pro.tonal.all.spring_boot_3.infrastructure.utility.OSSTools
import java.io.InputStream

@Named
class PictureService(private var pictureRepository:PictureRepository) {
    private val ossTools = OSSTools()
    fun createPicture(title:String, stream: InputStream, fileDetail: FormDataContentDisposition): Picture {
        val picture = Picture().apply {
            this.title = title
        }
        ossTools.putPicture(picture,stream,fileDetail)
        return pictureRepository.save(picture)
    }

    fun getAllPicture(): List<Picture> {
        return pictureRepository.findAll()
    }

}