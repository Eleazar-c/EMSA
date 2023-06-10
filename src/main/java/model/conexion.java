/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eleazar
 */
public class conexion {
    private static String server= "";
    private static String puerto = "";
    private static String bd = "";
     private static String url = "";
    private static String user = "";
    private static String password = "";
    
   
    public static Connection getConection(){
       try{
           Connection connection = DriverManager.getConnection(url,user,password);            
           System.out.println("conexion exitosa");
           return connection;
       }
       catch (SQLException e) {
           System.out.println(e.toString());
           return null;
       }
    }
}
