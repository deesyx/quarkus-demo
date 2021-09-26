package org.acme.service

import org.acme.resource.AttachmentCommand
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ExcelService {

    private val _log = Logger.getLogger(this::class.java)

    fun excel(attachmentCommand: AttachmentCommand) {
        val path = attachmentCommand.attachmentBody()
        try {
            XSSFWorkbook(path.toFile()).use { row ->
                val datatypeSheet = row.getSheetAt(0)
                datatypeSheet.rowIterator().asSequence().forEach { readRow(it) }
            }
        } catch (e: Exception) {
            _log.error("read excel failed", e)
        }
    }

    private fun readRow(row: Row) {
        println(row.getCell(0).stringCellValue)
        println(row.getCell(1).stringCellValue)
    }
}
