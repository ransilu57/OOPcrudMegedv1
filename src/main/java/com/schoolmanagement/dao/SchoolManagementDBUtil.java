package com.schoolmanagement.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.schoolmanagement.bean.DBConnect;
import com.schoolmanagement.bean.Payment;
import com.schoolmanagement.bean.Request;

public class SchoolManagementDBUtil {

    private static Connection con = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    // ------------------ Request Methods ------------------
    
    private static final String INSERT_REQUESTS_SQL = "INSERT INTO scholrequest (sid, type, description) VALUES  (?, ?, ?)";
    private static final String SELECT_REQUEST_BY_ID = "SELECT * FROM scholrequest WHERE id = ?";
    private static final String DELETE_REQUESTS_SQL = "DELETE FROM scholrequest WHERE id = ?";
    private static final String UPDATE_REQUESTS_SQL = "UPDATE scholrequest SET sid = ?, type = ?, description = ? WHERE id = ?";

    public static void insertRequest(Request request) throws SQLException {
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_REQUESTS_SQL)) {
            preparedStatement.setString(1, request.getSid());
            preparedStatement.setString(2, request.getType());
            preparedStatement.setString(3, request.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static Request selectRequest(int id) throws SQLException {
        Request request = null;
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_REQUEST_BY_ID)) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String sid = rs.getString("sid");
                String type = rs.getString("type");
                String description = rs.getString("description");
                Timestamp req_date = rs.getTimestamp("req_date");
                String status = rs.getString("status");
                request = new Request(id, sid, type, description, req_date, status);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return request;
    }

    public static List<Request> selectAllRequests(String sid) throws SQLException {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM scholrequest WHERE sid = ?";
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, sid);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String description = rs.getString("description");
                Timestamp req_date = rs.getTimestamp("req_date");
                String status = rs.getString("status");
                requests.add(new Request(id, sid, type, description, req_date, status));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return requests;
    }

    public static boolean deleteRequest(int id) throws SQLException {
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement statement = con.prepareStatement(DELETE_REQUESTS_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public static boolean updateRequest(Request request) throws SQLException {
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement statement = con.prepareStatement(UPDATE_REQUESTS_SQL)) {
            statement.setString(1, request.getSid());
            statement.setString(2, request.getType());
            statement.setString(3, request.getDescription());
            statement.setInt(4, request.getId());
            return statement.executeUpdate() > 0;
        }
    }

    // ------------------ Payment Methods ------------------

    public static boolean insertPayment(String studentId, BigDecimal paymentAmount, String paymentDate, String paymentMethod,
                                        String paymentStatus, String receiptNumber, String paymentReference, String paymentNotes) {
        String insertQuery = "INSERT INTO payments (student_id, payment_amount, payment_date, payment_method, " +
                             "payment_status, receipt_number, payment_reference, payment_notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setBigDecimal(2, paymentAmount);
            preparedStatement.setString(3, paymentDate);
            preparedStatement.setString(4, paymentMethod);
            preparedStatement.setString(5, paymentStatus);
            preparedStatement.setString(6, receiptNumber);
            preparedStatement.setString(7, paymentReference);
            preparedStatement.setString(8, paymentNotes);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    public static boolean isPaymentExists(String studentId, String paymentDate, String receiptNumber) {
        String selectQuery = "SELECT COUNT(*) FROM payments WHERE student_id = ? AND payment_date = ? AND receipt_number = ?";
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, paymentDate);
            preparedStatement.setString(3, receiptNumber);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public static boolean updatePayment(int paymentId, String studentId, BigDecimal paymentAmount, String paymentDate,
                                        String paymentMethod, String paymentStatus, String receiptNumber, String paymentReference, String paymentNotes) {
        String updateQuery = "UPDATE payments SET payment_amount = ?, payment_date = ?, payment_method = ?, payment_status = ?, " +
                             "receipt_number = ?, payment_reference = ?, payment_notes = ? WHERE id = ?";
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setBigDecimal(1, paymentAmount);
            preparedStatement.setString(2, paymentDate);
            preparedStatement.setString(3, paymentMethod);
            preparedStatement.setString(4, paymentStatus);
            preparedStatement.setString(5, receiptNumber);
            preparedStatement.setString(6, paymentReference);
            preparedStatement.setString(7, paymentNotes);
            preparedStatement.setInt(8, paymentId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    public static boolean deletePayment(int paymentId) throws SQLException {
        String deleteQuery = "DELETE FROM payments WHERE id = ?";
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, paymentId);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String selectQuery = "SELECT * FROM payments";
        con = DBConnect.getInstance().getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setId(resultSet.getInt("id"));
                payment.setStudentId(resultSet.getString("student_id"));
                payment.setPaymentAmount(resultSet.getBigDecimal("payment_amount"));
                payment.setPaymentDate(resultSet.getString("payment_date"));
                payment.setPaymentMethod(resultSet.getString("payment_method"));
                payment.setPaymentStatus(resultSet.getString("payment_status"));
                payment.setReceiptNumber(resultSet.getString("receipt_number"));
                payment.setPaymentReference(resultSet.getString("payment_reference"));
                payment.setPaymentNotes(resultSet.getString("payment_notes"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return payments;
    }

    // ------------------ Student Methods ------------------

    public static boolean validate(String sid, String password) {
        String sql = "SELECT * FROM student WHERE sid = ? AND password = ?";
        con = DBConnect.getInstance().getConnection();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sid);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    // ------------------ Utility ------------------

    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
