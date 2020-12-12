/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.*;

/**
 *
 * @author User
 */
public class Conexion {

  private static final String SQL_URL = "jdbc:mysql://localhost:3306/prueba?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String SQL_USER = "root";
    private static final String SQL_PASSWORD = "root";
    

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASSWORD);
        
    }

    public static void close(Statement st) throws SQLException {

        st.close();
    }
    
    public static void close(PreparedStatement pst) throws SQLException {

        pst.close();
    }

    public static void close(Connection cn) throws SQLException {

        cn.close();
    }

    public static void close(ResultSet rs) throws SQLException {

        rs.close();
    }

}
