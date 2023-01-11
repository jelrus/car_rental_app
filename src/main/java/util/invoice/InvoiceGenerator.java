package util.invoice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import exceptions.DocumentCreateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.invoice.objects.AbstractInvoice;
import util.invoice.objects.Member;
import util.invoice.objects.impl.DamageRefundInvoice;
import util.invoice.objects.impl.StandardInvoice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public final class InvoiceGenerator {

    private static final String OUTPUT_DIR = System.getProperty("catalina.base") + "/logs/invoices/";
    private static final String STANDARD_TEMPLATE = "/invoice/s_invoice_template.png";
    private static final String DAMAGE_REFUND_TEMPLATE = "/invoice/dr_invoice_template.png";

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private InvoiceGenerator() {}

    public static String generateStandardInvoice(StandardInvoice invoice) {
        createOutputIfNotExist();
        String pathToTemplate = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(STANDARD_TEMPLATE));
        Document document = new Document(PageSize.A4);
        String fileName = "invoice" + System.currentTimeMillis() + ".pdf";
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_DIR + fileName));
            document.open();

            setupMembers(invoice, writer);

            insertAndAdjustText(invoice.getFullTitle(), 67, 400, writer);
            insertAndAdjustText(invoice.getRentalPrice(), 375, 400, writer);
            insertAndAdjustText(invoice.getDaysOfUsage(), 460, 400, writer);
            insertAndAdjustText(invoice.getTotalPrice(), 520, 400, writer);

            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = Image.getInstance(pathToTemplate);
            image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);
            document.close();
        } catch (IOException | DocumentException e){
            LOGGER_ERROR.error("Standard invoice creation failed");
        }

        return fileName;
    }

    public static String generateDamageRefundInvoice(DamageRefundInvoice invoice) {
        createOutputIfNotExist();
        String pathToTemplate = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(DAMAGE_REFUND_TEMPLATE));
        Document document = new Document(PageSize.A4);
        String fileName = "invoice" + System.currentTimeMillis() + ".pdf";
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_DIR + fileName));
            document.open();
            setupMembers(invoice, writer);
            setupDamageRefundMap(invoice, writer);
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = Image.getInstance(pathToTemplate);
            image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);
            document.close();
        } catch (IOException | DocumentException e){
            LOGGER_ERROR.error("Damage refund invoice creation failed");
            throw new DocumentCreateException("Document creation failed");
        }

        return fileName;
    }

    public static Member generateCompany() {
        Member sender = new Member();
        sender.setName("Car Rental Co.");
        sender.setCountry("Ukraine");
        sender.setZipCode("01001");
        sender.setCity("Kiev");
        sender.setStreet("Hmelnitskoho av.");
        sender.setBuilding("b.110");
        sender.setPhone("+380975502415");
        sender.setEmail("carrental@mail.com");
        return sender;
    }

    private static void setupMembers(AbstractInvoice invoice, PdfWriter writer) {
        insertAndAdjustText(invoice.getCreation(), 460, 735, writer);
        insertAndAdjustText(invoice.getNumber(), 445, 710, writer);

        insertAndAdjustText(invoice.getSender().getName(), 67, 630, writer);
        insertAndAdjustText(invoice.getSender().getGlobalAddress(), 67, 605, writer);
        insertAndAdjustText(invoice.getSender().getLocalAddress(), 67, 580, writer);
        insertAndAdjustText(invoice.getSender().getPhone(), 67, 555, writer);
        insertAndAdjustText(invoice.getSender().getEmail(), 67, 530, writer);

        insertAndAdjustText(invoice.getRecipient().getName(), 390, 630, writer);
        insertAndAdjustText(invoice.getRecipient().getGlobalAddress(), 390, 605, writer);
        insertAndAdjustText(invoice.getRecipient().getLocalAddress(), 390, 580, writer);
        insertAndAdjustText(invoice.getRecipient().getPhone(), 390, 555, writer);
        insertAndAdjustText(invoice.getRecipient().getEmail(), 390, 530, writer);
    }

    private static void setupDamageRefundMap(DamageRefundInvoice invoice, PdfWriter writer) {
        int decrementY = 0;
        BigDecimal cost = new BigDecimal("0.00");

        for (Map.Entry<String, String> me: invoice.getDamageCost().entrySet()) {
            insertAndAdjustText(me.getKey(), 67, 420 + decrementY, writer);
            insertAndAdjustText(me.getValue(), 500, 420 + decrementY, writer);
            decrementY -= 25;
            cost = cost.add(new BigDecimal(me.getValue()));
        }

        insertAndAdjustText(cost.toPlainString(), 500, 138, writer);
    }

    private static void insertAndAdjustText(String text, float x, float y, PdfWriter writer) {
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf;
        try {
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.beginText();
            cb.saveState();
            cb.moveText(x, y);
            cb.setFontAndSize(bf, 12);
            cb.showText(text);
            cb.endText();
            cb.restoreState();
        } catch (DocumentException | IOException e) {
            LOGGER_ERROR.error("Text insertion failed");
        }
    }

    private static void createOutputIfNotExist() {
        try {
            Files.createDirectory(Paths.get(OUTPUT_DIR));
        } catch (IOException e){
            LOGGER_ERROR.error("Output folder not created");
        }
    }
}