package test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.text.TableView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException, FileNotFoundException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        document.close();
    }
}
