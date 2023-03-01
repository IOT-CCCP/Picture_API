package pro.tonal.all.spring_boot_3.configuration

import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.Path
import jakarta.ws.rs.ext.Provider
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.util.ClassUtils
import java.util.*
import java.util.stream.Collectors

@Configuration
@ApplicationPath("/api")
class JerseyConfiguration : ResourceConfig() {
    init {
        scanPackages("pro.tonal.all.spring_boot_3.resource")
    }

    /**
     * Jersey的packages()方法在Jar形式运行下有问题，这里修理一下
     *
     */
    private fun scanPackages(scanPackage: String) {
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(Path::class.java))
        scanner.addIncludeFilter(AnnotationTypeFilter(Provider::class.java))
        this.registerClasses(scanner.findCandidateComponents(scanPackage).stream()
            .map { beanDefinition: BeanDefinition ->
                Objects.requireNonNull(
                    beanDefinition.beanClassName
                )?.let {
                    ClassUtils.resolveClassName(
                        it, this.classLoader
                    )
                }
            }
            .collect(Collectors.toSet()))
    }


}