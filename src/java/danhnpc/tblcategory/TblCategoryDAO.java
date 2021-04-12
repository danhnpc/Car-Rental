/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblcategory;

import danhnpc.tblcar.TblCarDTO;
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
 * @author visug
 */
public class TblCategoryDAO implements Serializable{
    List<TblCategoryDTO> category;

    public List<TblCategoryDTO> getCategory() {
        return category;
    }
    
    public void getAllCategory()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Select categoryID, categoryName From tblCategory ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    TblCategoryDTO dto = new TblCategoryDTO(categoryID, categoryName);
                    if(this.category == null){
                        this.category = new ArrayList<>();
                    }
                    this.category.add(dto);
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
