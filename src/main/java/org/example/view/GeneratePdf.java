package org.example.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.model.Bill;
import org.example.model.User;
import org.example.service.book.BookServiceImpl;

import java.io.*;
import java.util.List;

public class GeneratePdf
{

    private BookServiceImpl bookService;

    public GeneratePdf(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public void createPdfForOneEmployee(List<Bill> allBillsOfAnEmployee) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:\\Materii Faculta\\Anu III Semestru I\\IS\\Laborator\\Proiect\\OneEmployeeReport.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);

        Paragraph paragraph1 = new Paragraph("All the bills for you employee", font);
        document.add(paragraph1);

        document.add(new Paragraph("\n"));

        for (Bill bill : allBillsOfAnEmployee) {
            Paragraph paragraph2 = new Paragraph("Bill id: " + bill.getId() + "\n" +
                    "Book id: " + bill.getBook_id() + "\n" +
                    "Employee id: " + bill.getCustomer_id() + "\n" +
                    "Quantity: " + bill.getQuantity() + "\n" +
                    "Amount paid: " + bill.getAmountPaid() + " lei \n", font2);
            document.add(paragraph2);
            document.add(new Paragraph("\n"));
        }

        document.close();
    }

    public void createPdfForAllEmployees(List<User> allUsers) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:\\Materii Faculta\\Anu III Semestru I\\IS\\Laborator\\Proiect\\AllTheEmployeesReport.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);

        Paragraph paragraph1 = new Paragraph("All the bills for this employee", font);
        document.add(paragraph1);

        document.add(new Paragraph("\n"));

        for (User user : allUsers) {
            Paragraph paragraph3 = new Paragraph("Employee with id: " + user.getId() + "\n", font);
            document.add(paragraph3);

            document.add(new Paragraph("\n"));

            List<Bill> allBillsOfAnEmployee = bookService.findAllBillsOfAnEmployee(user.getId());

            for (Bill bill : allBillsOfAnEmployee) {
                Paragraph paragraph2 = new Paragraph("  Bill id: " + bill.getId() + "\n" +
                        "   Book id: " + bill.getBook_id() + "\n" +
                        "   Employee id: " + bill.getCustomer_id() + "\n" +
                        "   Quantity: " + bill.getQuantity() + "\n" +
                        "   Amount paid: " + bill.getAmountPaid() + " lei \n", font2);
                document.add(paragraph2);
                document.add(new Paragraph("\n"));
            }

        }

        document.close();
    }
}
