package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo {

    private conexion connPosgres;

    public modelo(){
        this.connPosgres= new conexion();
    }
    
    public ResultSet ejecutarConsulta(String consulta) {
        try {
            Statement conn = this.connPosgres.getConection().createStatement();
            return conn.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
}
