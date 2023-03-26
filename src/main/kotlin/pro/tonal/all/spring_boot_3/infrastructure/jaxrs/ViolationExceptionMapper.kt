/*
 * Copyright 2012-2020. the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. More information from:
 *
 *        https://github.com/fenixsoft
 */
package pro.tonal.all.spring_boot_3.infrastructure.jaxrs

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.slf4j.LoggerFactory
import java.util.stream.Collectors

/**
 * 用于统一处理在Resource中由于验证器验证失败而带回客户端的错误信息
 *
 * @author icyfenix@gmail.com
 * @date 2020/3/10 23:37
 */
@Provider
class ViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {
    override fun toResponse(exception: ConstraintViolationException): Response {
        log.warn("客户端传入了校验结果为非法的数据", exception)
        val msg = exception.constraintViolations.stream().map { obj: ConstraintViolation<*> -> obj.message }
            .collect(Collectors.joining("；"))
        return CommonResponse.send(Response.Status.BAD_REQUEST, msg)
    }

    companion object {
        private val log = LoggerFactory.getLogger(ViolationExceptionMapper::class.java)
    }
}