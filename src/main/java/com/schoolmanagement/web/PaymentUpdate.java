package com.schoolmanagement.web;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.schoolmanagement.dao.SchoolManagementDBUtil;





@WebServlet("/updatePayment")
public class PaymentUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        String studentId = request.getParameter("student_id");
        String paymentIdStr = request.getParameter("id"); // Assuming payment ID is included in the form
        String paymentAmountStr = request.getParameter("payment_amount");
        String paymentDate = request.getParameter("payment_date");
        String paymentMethod = request.getParameter("payment_method");
        String paymentStatus = request.getParameter("payment_status");
        String receiptNumber = request.getParameter("receipt_number");
        String paymentReference = request.getParameter("payment_reference");
        String paymentNotes = request.getParameter("payment_notes");

        try {
            
            BigDecimal paymentAmount = new BigDecimal(paymentAmountStr);
            int paymentId = Integer.parseInt(paymentIdStr); 

           
            boolean isUpdated = SchoolManagementDBUtil.updatePayment(paymentId, studentId, paymentAmount, paymentDate, paymentMethod, paymentStatus, receiptNumber, paymentReference, paymentNotes);

            
            String targetJSP = isUpdated ? "paymentUpdateSuccessful.jsp" : "paymentUpdateUnsuccessful.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(targetJSP);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
           
            request.setAttribute("errorMessage", "Invalid payment amount or ID format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
           
            request.setAttribute("errorMessage", "An error occurred while updating the payment: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
