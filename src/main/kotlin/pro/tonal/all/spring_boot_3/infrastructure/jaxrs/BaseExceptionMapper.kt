package pro.tonal.all.spring_boot_3.infrastructure.jaxrs

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.slf4j.LoggerFactory

@Provider
class BaseExceptionMapper: ExceptionMapper<Throwable> {
    override fun toResponse(exception: Throwable): Response {
        log.error(exception.message, exception)
        return CommonResponse.failure(exception.message)
    }
    companion object {
        private val log = LoggerFactory.getLogger(BaseExceptionMapper::class.java)
    }
}