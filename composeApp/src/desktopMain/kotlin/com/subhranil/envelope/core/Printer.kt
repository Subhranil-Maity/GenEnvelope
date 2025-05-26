package com.subhranil.envelope.core

import java.awt.Font
import java.awt.Graphics2D
import java.awt.font.TextAttribute
import java.awt.print.Printable
import java.awt.print.PrinterJob
import java.text.AttributedString

fun printer(
    senderAddress: List<String>,
    recipientAddress: List<String>,
    senderFont: Font = Font("Serif", Font.PLAIN, 10),
    recipientFont: Font = Font("Serif", Font.BOLD, 14)
) {
    val config = EnvelopeConfigBuilder().build(senderAddress, recipientAddress)
    val job = PrinterJob.getPrinterJob()

    job.setPrintable { graphics, pageFormat, pageIndex ->
        if (pageIndex > 0) return@setPrintable Printable.NO_SUCH_PAGE

        val g2d = graphics as Graphics2D


        // Rotate for landscape envelope
        g2d.rotate(Math.toRadians(-90.0))
        g2d.translate(-pageFormat.height, 0.0)

        val baseFont = senderFont.deriveFont(Font.BOLD)
        val attributed = AttributedString("IF UNDELIVERED PLEASE RETURN TO:-").apply {
            addAttribute(TextAttribute.FONT, baseFont)
            addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)
        }
        g2d.drawString(attributed.iterator, config.senderX, config.senderY - config.senderLineSpacing)
        g2d.font = senderFont
        senderAddress.forEachIndexed { index, line ->
            val x = config.senderX
            val y = config.senderY + (index * config.senderLineSpacing)
            g2d.drawString(line, x, y)
        }

        // Recipient Address (center-ish)
        g2d.font = recipientFont
        recipientAddress.forEachIndexed { index, line ->
            val x = config.recipientX
            val y = config.recipientY + (index * config.recipientLineSpacing)
            g2d.drawString(line, x, y)
        }

        Printable.PAGE_EXISTS
    }

    if (job.printDialog()) {
        job.print()
    }
}