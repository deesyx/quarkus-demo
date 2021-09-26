package org.acme.resource

import org.jboss.resteasy.plugins.providers.multipart.InputPart
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import javax.ws.rs.FormParam

data class AttachmentCommand(
        @field:FormParam("file")
        var file: InputPart? = null
) {
    fun attachmentBody(): Path {
        val inputStream = this.file?.getBody(InputStream::class.java, null)!!
        val tempFile = Files.createTempFile("upload-", ".xlsx")
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING)
        return tempFile
    }
}
