package pro.tonal.all.spring_boot_3.resource

import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.stereotype.Component
import pro.tonal.all.spring_boot_3.applicaition.PictureApplicationService
import pro.tonal.all.spring_boot_3.domain.picture.Picture
import pro.tonal.all.spring_boot_3.infrastructure.jaxrs.CommonResponse
import java.io.InputStream
import java.util.*

@Path("/picture")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PictureResource {
    @Inject
    private lateinit var service:PictureApplicationService
    @GET
    @Path("/{id}")
    fun getPicture(@PathParam("id") id:Int?): Optional<Picture> {
        return service.findPictureById(id)
    }
    @GET
    @Path("/all")
    fun getAllPicture(): List<Picture> {
        return service.getAllPicture()
    }
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    fun createPicture(@FormDataParam("title") title:String,
                      @FormDataParam("file") stream:InputStream,
                      @FormDataParam("file") fileDetail: FormDataContentDisposition,
    ): Picture {
        return service.createPicture(title, stream, fileDetail)
    }
    @PUT
    fun updatePicture(@Valid picture: Picture): Response {
        return CommonResponse.op({ service.updatePicture(picture) })
    }
    @DELETE
    @Path("/{id}")
    fun deletePicture(@PathParam("id") id:Int?): Response {
        return CommonResponse.op({ service.deletePicture(id) })
    }
}