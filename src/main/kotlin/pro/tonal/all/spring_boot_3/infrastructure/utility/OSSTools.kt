package pro.tonal.all.spring_boot_3.infrastructure.utility

import com.aliyun.oss.*
import com.aliyun.oss.common.comm.Protocol
import com.aliyun.oss.model.PutObjectRequest
import jakarta.inject.Named
import jakarta.validation.Valid
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import pro.tonal.all.spring_boot_3.domain.picture.Picture
import java.io.InputStream
import java.text.SimpleDateFormat




@Named
open class OSSTools {
    @Value("\${aliyun.domain}")
    private lateinit var domain:String

    @Value("\${aliyun.bucketName}")
    private lateinit var bucketName:String

    @Value("\${aliyun.accessKeyId}")
    private lateinit var accessKeyId:String

    @Value("\${aliyun.accessKeySecret}")
    private lateinit var accessKeySecret:String
    @Value("\${aliyun.dir}")
    private lateinit var dir:String

    fun putPicture(p: Picture, stream: InputStream, fileDetail: FormDataContentDisposition) {
        var fileName = fileDetail.fileName
        val ossClint:OSS = getOSSClint()
        val sufix = fileName?.split(".")?.get(1) ?:"jpg"
        val date = SimpleDateFormat("_yyyyMMdd-hhmmss").format(System.currentTimeMillis())
        fileName = p.title+date+"."+sufix
        try {
            ossClint.putObject(PutObjectRequest(bucketName,dir.substring(1)+"/"+fileName,stream))
            p.url = "https://${bucketName}.${domain}${dir}/$fileName"
        }catch (oe: OSSException){
            println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.")
            println("Error Message:" + oe.errorMessage)
            println("Error Code:" + oe.errorCode)
            println("Request ID:" + oe.requestId)
            println("Host ID:" + oe.hostId)
        }catch (ce:ClientException){
            println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.")
            println("Error Message:" + ce.message)
        }finally {
            ossClint.shutdown()
        }
    }

    fun deletePicture(@Valid p: Picture){
        val ossClint:OSS = getOSSClint()
        val fileName = p.url?.let { it.substring(it.lastIndexOf(dir)) }
        try {
            ossClint.deleteObject(bucketName,fileName)
        }catch (oe:OSSException){
            println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.")
            println("Error Message:" + oe.errorMessage)
            println("Error Code:" + oe.errorCode)
            println("Request ID:" + oe.requestId)
            println("Host ID:" + oe.hostId)
        }catch (ce:ClientException){
            println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.")
            println("Error Message:" + ce.message)
        }finally {
            ossClint.shutdown()
        }



    }

    @Bean
    private fun getOSSClint(): OSS {
        val endpoint = "https://${domain}"
        val clientConfig = ClientBuilderConfiguration()
        clientConfig.protocol = Protocol.HTTPS
        return OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, clientConfig)
    }
}