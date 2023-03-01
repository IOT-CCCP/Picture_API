package pro.tonal.all.spring_boot_3.configuration.jerseyProvider

import jakarta.servlet.http.HttpServletResponse
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerResponseContext
import jakarta.ws.rs.container.ContainerResponseFilter
import jakarta.ws.rs.ext.Provider
import org.glassfish.jersey.server.ContainerRequest
import org.glassfish.jersey.server.ContainerResponse
import java.io.IOException

@Provider
class ResponseFilter : ContainerResponseFilter {
    fun filter(creq: ContainerRequest?, cres: ContainerResponse): ContainerResponse {
        cres.headers.add("Access-Control-Allow-Origin", "*")
        cres.headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        cres.headers.add("Access-Control-Allow-Credentials", "true")
        cres.headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        cres.headers.add("Access-Control-Max-Age", "1209600")
        return cres
    }

    @Throws(IOException::class)
    override fun filter(request: ContainerRequestContext, cres: ContainerResponseContext) {
        if ("OPTIONS".equals(request.method, ignoreCase = true)) { //浏览器会先通过options请求来确认服务器是否可以正常访问，此时应放行
            cres.status = HttpServletResponse.SC_OK
        }
        cres.headers.add("Access-Control-Allow-Origin", "*")
        cres.headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        cres.headers.add("Access-Control-Allow-Credentials", "true")
        cres.headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        cres.headers.add("Access-Control-Max-Age", "1209600")
    }
}