/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.servlets;

import danhnpc.tblcar.TblCarDAO;
import danhnpc.tblcar.TblCarDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author visug
 */
@WebServlet(name = "SearchCarServlet", urlPatterns = {"/SearchCarServlet"})
public class SearchCarServlet extends HttpServlet {

    private final String SEARCH_PAGE = "searchCar.jsp";

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
        String url = SEARCH_PAGE;
        boolean err = false;
        try {
            String searchValue = request.getParameter("txtSearchValue");
            String category = request.getParameter("txtCategory");
            String dateRental = request.getParameter("txtDateRental");
            if (dateRental.trim().equals("")) {
                request.setAttribute("RENTALDATEERROR", "Please choose rental date");
                err = true;
            }
            String dateReturn = request.getParameter("txtDateReturn");
            if (dateReturn.trim().equals("")) {
                request.setAttribute("RETURNDATEERROR", "Please choose return date");
                err = true;
            }
            String amount = request.getParameter("txtQuantity");
            if (amount.trim().equals("")) {
                amount = "0";
            }

            if (!err) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date rental = sdf.parse(dateRental);
                Date returnDate = sdf.parse(dateReturn);
                long total = returnDate.getDate() - rental.getDate();
                if (total < 1) {
                    request.setAttribute("TOTALDATEERROR", "Renturn day must be greater than 1");
                    return;
                }
                TblCarDAO dao = new TblCarDAO();
                dao.searchCar(searchValue, category, Integer.parseInt(amount));
                List<TblCarDTO> list = dao.getListSearch();
                
                HttpSession session = request.getSession();
                int page = 0;
                List<TblCarDTO> cars = new ArrayList<>();
                for(int i = page; i < list.size() ; i++){
                    if(cars.size() < 8){
                        cars.add(list.get(i));
                    }
                }
                session.setAttribute("PAGE", page);
                request.setAttribute("LISTCARPAGING", cars);
                session.setAttribute("LISTCAR", list);
                request.setAttribute("RENTALDATE", total);
            }

        } catch (ParseException ex) {
            log("SearchCarServlet_ParseException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SearchCarServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchCarServlet_NamingException: " + ex.getMessage());
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
