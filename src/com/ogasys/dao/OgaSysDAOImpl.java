package com.ogasys.dao;

import com.ogasys.model.SignInModel;
import com.ogasys.util.Encryptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class OgaSysDAOImpl implements OgaSysDAO {

    private PreparedStatement pst;
    private final Connection con;

    
    public OgaSysDAOImpl() throws ClassNotFoundException, SQLException {
        con = DBConnection.getInstance().getConnection();
    }
    

    public SignInModel SignInValidate(SignInModel model) throws SQLException 
    {
        String sql = "SELECT * from User where Email=? AND Pass=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, model.email);
        pst.setString(2, model.pass);
        ResultSet rs=pst.executeQuery();
        if(rs.next())
        {
            model.setName(rs.getString("Fname"));
            model.setId(rs.getInt("UserId"));
            return model;
        }
        return null;
    }
    
}