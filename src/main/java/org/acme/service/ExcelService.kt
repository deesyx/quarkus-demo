package org.acme.service

import jxl.Workbook
import org.acme.resource.AttachmentCommand
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ExcelService {

    private val _log = Logger.getLogger(this::class.java)

    fun excel(attachmentCommand: AttachmentCommand) {
        try {
            val workbook = Workbook.getWorkbook(attachmentCommand.attachmentBody2())
            val sheet = workbook.getSheet(0)
            val rows = sheet.rows
            for (row in 0 until rows) {
                println(sheet.getCell(0, row).contents)
                println(sheet.getCell(1, row).contents)
            }
        } catch (e: Exception) {
            _log.error("read excel failed", e)
        }
    }
}
