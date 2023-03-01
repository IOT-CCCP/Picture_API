package pro.tonal.all.spring_boot_3.configuration.jerseyProvider

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.ext.Provider
import java.io.IOException

@Provider
class RequestFilter : ContainerRequestFilter {
    @Throws(IOException::class)
    override fun filter(requestContext: ContainerRequestContext) {
    }
}