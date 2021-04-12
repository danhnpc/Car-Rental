/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblcar;

import danhnpc.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class TblCarDAO implements Serializable {
    
    List<TblCarDTO> home;

    public List<TblCarDTO> getHome() {
        return home;
    }

    public void get20Car() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Select ID, name, image, color, year, categoryID, price, quantity, status From tblCar ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    String id = rs.getString("ID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    String categoryID = rs.getString("categoryID");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    boolean active = rs.getBoolean("status");
                    if(active){
                        if(this.home == null){
                            this.home = new ArrayList<>();
                        }
                        TblCarDTO dto = new TblCarDTO(id, name, image, color, year, categoryID, price, quantity, active);
                        if(this.home.size() < 20){
                            this.home.add(dto);
                        }
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }

        }
    }
    
    private List<TblCarDTO> listSearch;

    public List<TblCarDTO> getListSearch() {
        return listSearch;
    }
    
    public void searchCar(String carName, String category, int amount )
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Select ID, name, image, color, year, categoryID, price, quantity, status From tblCar "
                        + "Where name Like ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + carName + "%");
                rs = ps.executeQuery();
                while(rs.next()){
                    String id = rs.getString("ID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    String categoryID = rs.getString("categoryID");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    boolean active = rs.getBoolean("status");
                    if(active){
                        if(this.listSearch == null){
                            this.listSearch = new ArrayList<>();
                        }
                        if( (category.equals("0") || category.equals(categoryID)) && (quantity >= amount) ){
                            TblCarDTO dto = new TblCarDTO(id, name, image, color, year, categoryID, price, quantity, active);
                            this.listSearch.add(dto);
                        }
                        
                        
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }

        }
    }
    
}
