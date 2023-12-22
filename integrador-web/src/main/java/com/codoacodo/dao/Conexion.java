package com.codoacodo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    
    public static Connection getConexion() 
    {
     String JDBC_URL = "jdbc:mysql://localhost:3306/integrador_cac";
     String JDBC_USR = "root";
     String JDBC_PASS = "robert93";
    
     String driverName = "com.mysql.cj.jdbc.Driver";
      
     Connection con = null;       
             
      try
      {
          Class.forName(driverName);
          con = DriverManager.getConnection(JDBC_URL, JDBC_USR, JDBC_PASS);
      }catch(Exception e)
      {
          e.printStackTrace();
      }
       return con;
    }
    public static void main(String[] args)
    {
        Connection con = Conexion.getConexion();
        if(con != null)
        {
            System.out.println("Conexion OK");
        }
        else
        {
            System.err.println("Conexion FAILL");
        }
    }
     
}
