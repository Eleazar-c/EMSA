package model;
import clases.evento;

import java.sql.*;
import java.util.ArrayList;

public class eventoModelo {

    private conexion connPosgres;

    public eventoModelo(){
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
    public boolean registrarEvento(evento evento, ArrayList<evento> eventosArray){


        try {
            String SQL = "INSERT INTO evento(\"nombreevento\", \"descripcion\", \"fechainicio\", \"fechafinal\", \"img\", \"vip\", \"plant_a\", \"plan_b\", \"vipmg\") "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            Connection connection = this.connPosgres.getConection();

            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, evento.getNombreEvento() );
            sentencia.setString(2, evento.getSinopsis());
            sentencia.setString(3, evento.getFechaInicioVisible());
            sentencia.setString(4,evento.getFechaFinalVisible());
            sentencia.setString(5,evento.getLinkImg());
            sentencia.setFloat(6,evento.getVIP());
            sentencia.setFloat(7,evento.getPantlaA());
            sentencia.setFloat(8,evento.getPlantaB());
            sentencia.setFloat(9,evento.getPrecioVIPMG());

            sentencia.executeUpdate();

            //ingresamos las fechas

            sentencia.close();

            return true;

        } catch (Exception e) {
            System.err.println("Ocurrio un error al registrar la tarea.");
            System.err.println("Mensaje del error: "+ e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }


    }
}
