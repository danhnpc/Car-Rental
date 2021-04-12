/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.servlets;

import danhnpc.tblusers.TblUserCreateError;
import danhnpc.tblusers.TblUsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author visug
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String ERROR = "createAccount.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR;
        TblUserCreateError error = new TblUserCreateError();
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String phone = request.getParameter("txtPhone");
            String fullName = request.getParameter("txtFullName");
            String address = request.getParameter("txtAddress");
            Date createDate = new Date(System.currentTimeMillis());
            boolean errFound = false;
            if(!email.matches("[a-zA-Z0-9]+@\\w+[.][\\w.]+")){
                error.setEmailErr("Email is incorrect data format");
                errFound = true;
            }
            if(password.trim().length() == 0){
                error.setPasswordErr("Password is not empty");
                errFound = true;
            }else if(!confirm.trim().equals(password.trim())){
                error.setConfirmErr("Confirm password is not matched!");
                errFound = true;
            }
            if(!phone.matches("\\d{10}")){
                error.setPhoneErr("Phone number must be 10 numbers");
                errFound = true;
            }
            if(fullName.trim().length() == 0 ){
                error.setNameErr("Name is not empty");
                errFound = true;
            }
            if(address.trim().length() == 0){
                error.setAddressErr("Address is not empty");
                errFound = true;
            }
            if(errFound){
                request.setAttribute("CREATEERROR", error);
            }else{
                TblUsersDAO dao=  new TblUsersDAO();
                boolean result = dao.createAccount(email, password, phone, fullName, address, createDate, "active");
                if(result){
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            String err = ex.getMessage();
            if(err.contains("duplicate")){
                error.setEmailDuplicateErr("This email is existed!");
                request.setAttribute("CREATEERROR", error);
            }
        } catch (NamingException ex) {
            log("CreateAccountServlet_NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
