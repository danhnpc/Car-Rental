/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.servlets;

import danhnpc.tblcar.TblCarDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "PagingServlet", urlPatterns = {"/PagingServlet"})
public class PagingServlet extends HttpServlet {
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
        try {
            String button = request.getParameter("btAction");
            String total = request.getParameter("totalDay");
            HttpSession session = request.getSession();
            int page = (int) session.getAttribute("PAGE");
            List<TblCarDTO> list = (List<TblCarDTO>) session.getAttribute("LISTCAR");
            List<TblCarDTO> cars = new ArrayList<>();
            if(button.equals("Next")){
                if(page < (list.size()/8) ){
                    page = page + 1;
                }
                
                int nextPage = page * 8;
                for(int i = nextPage; i < list.size() ; i++){
                    if(cars.size() < 8 && nextPage < list.size() ){
                        cars.add(list.get(i));
                    }
                }
            }else if(button.equals("Previous")){
                page = page - 1;
                int nextPage = page * 8;
                for(int i = nextPage; i < list.size() ; i++){
                    if(cars.size() < 8 && nextPage < list.size() ){
                        cars.add(list.get(i));
                    }
                }
            }
            request.setAttribute("RENTALDATE", Long.parseLong(total));
            session.setAttribute("PAGE", page);
            request.setAttribute("LISTCARPAGING", cars);
        }finally{
            RequestDispatcher rd=  request.getRequestDispatcher(url);
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
