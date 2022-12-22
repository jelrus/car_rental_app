package view.controller.invoice;

import facade.relation.InvoicesOrderFacade;
import facade.relation.impl.InvoicesOrderFacadeImpl;
import view.dto.response.relation.InvoicesOrderDtoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet("/download/invoice/")
public class DownloadInvoiceServlet extends HttpServlet {

    private final InvoicesOrderFacade invoicesOrderFacade = new InvoicesOrderFacadeImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long invoiceId = Long.parseLong(req.getParameter("id"));
        InvoicesOrderDtoResponse invoice = invoicesOrderFacade.findById(invoiceId);
        File file = new File(System.getProperty("catalina.base") + "/logs/invoices/" + invoice.getFileLink());

        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition", "attachment; filename=" + invoice.getFileLink() + ".pdf");

        try(InputStream in = new DataInputStream(new FileInputStream(file));
            OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[1048];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}