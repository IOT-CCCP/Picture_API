package pro.tonal.all.spring_boot_3.domain.picture


import jakarta.inject.Inject
import jakarta.inject.Named
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import pro.tonal.all.spring_boot_3.infrastructure.utility.OSSTools
import java.io.InputStream
import java.util.*

@Named
open class PictureService() {
    @Inject private lateinit var ossTools:OSSTools
    @Inject private lateinit var pictureRepository:PictureRepository
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
    fun findPictureById(id:Int): Optional<Picture> {
        return pictureRepository.findById(id)
    }
    fun updatePicture(picture: Picture){
        pictureRepository.save(picture)
    }
    fun deletePicture(id:Int){
        val p = pictureRepository.findById(id).get()
        ossTools.deletePicture(p)
        pictureRepository.delete(p)
    }

}