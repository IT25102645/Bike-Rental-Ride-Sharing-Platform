package com.example.bikerentalsystem;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "paymentServlet", value = "/payment-servlet")
public class PaymentServlet extends HttpServlet {

    private static final String FILE_PATH = "payments.txt";

    public void savePayment(Payment payment) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(payment.getPaymentID() + "," +
                payment.getUserID() + "," +
                payment.getAmount() + "," +
                payment.getStatus() + "\n");
        fw.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Payment Management</h1>");
        out.println("</body></html>");
    }

    public void destroy() {}
}