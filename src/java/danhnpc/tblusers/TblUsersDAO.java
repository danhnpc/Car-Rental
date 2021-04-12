/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblusers;

import danhnpc.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class TblUsersDAO implements Serializable{
    private String fullName;

    public String getFullName() {
        return fullName;
    }
    
    public boolean checkLogin(String email, String password)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Select email, name From tblUsers Where email=? and password=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                    fullName = rs.getString("name");
                    return true;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    
    public boolean createAccount(String email, String password, String phone, String name, String address, Date createDate, String status)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
    
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "INSERT INTO tblUsers Values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, phone);
                ps.setString(4, name);
                ps.setString(5, address);
                ps.setDate(6, createDate);
                ps.setString(7, status);
                int row = ps.executeUpdate();
                if(row > 0){
                    return true;
                }
            }
        }finally{
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
}
