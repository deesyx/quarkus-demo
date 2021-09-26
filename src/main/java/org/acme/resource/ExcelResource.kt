package org.acme.resource

import org.acme.service.ExcelService
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/excel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ExcelResource {

    @Inject
    lateinit var excelService: ExcelService

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    fun test(@MultipartForm attachmentCommand: AttachmentCommand): String {
        excelService.excel(attachmentCommand)
        return "success"
    }
}
