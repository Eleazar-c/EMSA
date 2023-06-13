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
    
   private static String server= "emsa.postgres.database.azure.com";
    private static String puerto = "5432";
    private static String bd = "emsa";
     private static String url = "jdbc:postgresql://"+server+":"+puerto+"/"+bd;//host=emsa.postgres.database.azure.com port=5432 dbname={your_database} user=emsa password={your_password}
    private static String user = "emsa";//emsa
    private static String password = "Cuellar1999!";
    
    
   
   
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
