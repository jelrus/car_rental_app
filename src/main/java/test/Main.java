package test;

import com.itextpdf.text.*;
import util.invoice.InvoiceGenerator;
import util.invoice.objects.AbstractInvoice;
import util.invoice.objects.Member;
import util.invoice.objects.impl.DamageRefundInvoice;
import util.invoice.objects.impl.StandardInvoice;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, DocumentException, ParseException {

        /*Member sender = new Member();
        sender.setName("Car Rental Co.");
        sender.setCountry("Ukraine");
        sender.setZipCode("87500");
        sender.setCity("Kiev");
        sender.setStreet("Hmelnitskoho av.");
        sender.setBuilding("b.110");
        sender.setPhone("+380975502415");
        sender.setEmail("carrental@mail.com");

        Member recipient = new Member();
        recipient.setName("Alex Five");
        recipient.setCountry("Ukraine");
        recipient.setZipCode("87500");
        recipient.setCity("Kiev");
        recipient.setStreet("Mazepy av.");
        recipient.setBuilding("b.99/b");
        recipient.setPhone("+380678882243");
        recipient.setEmail("alexfive@mail.com");

        *//*StandardInvoice invoice = new StandardInvoice(sender, recipient);
        invoice.setFullTitle("BMW X5 (Luxury SUV)");
        invoice.setRentalPrice("300.00");
        invoice.setDaysOfUsage("15");
        invoice.setTotalPrice("4500.00");
        String a = InvoiceGenerator.generateStandardInvoice(invoice);*//*

        DamageRefundInvoice dri = new DamageRefundInvoice(sender, recipient);
        Map<String, String> damageCost = Map.of("Engine","200.00",
                                                "Windshield", "300.00",
                                                "Door", "100.00");
        dri.setDamageCost(damageCost);
        BigDecimal cost = new BigDecimal("0.00");
        for (Map.Entry<String, String> me: dri.getDamageCost().entrySet()) {
            cost = cost.add(new BigDecimal(me.getValue()));
        }

        dri.setTotalCost(cost.toPlainString());

        String link = InvoiceGenerator.generateDamageRefundInvoice(dri);*/
    }
}
