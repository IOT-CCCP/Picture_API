package pro.tonal.all.spring_boot_3.infrastructure.jaxrs

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Slf4j
class BaseExceptionMapper: ExceptionMapper<Throwable> {
    private val log: Logger = LoggerFactory.getLogger(BaseExceptionMapper::class.java)
    override fun toResponse(exception: Throwable): Response {
        log.error(exception.message, exception)
        return CommonResponse.failure(exception.message)
    }
}