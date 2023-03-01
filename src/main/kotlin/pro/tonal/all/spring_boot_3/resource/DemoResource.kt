package pro.tonal.all.spring_boot_3.resource

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.springframework.stereotype.Component

@Path("/user")
@Component
class DemoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getMap():Map<String,String>{
        val map = HashMap<String, String>()
        map["name"] = "tonal-all"
        map["age"] = "21"
        return map
    }
}