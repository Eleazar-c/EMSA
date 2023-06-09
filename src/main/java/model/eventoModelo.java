package model;

import clases.evento;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class eventoModelo {

    private conexion connPosgres;

    public eventoModelo() {
        this.connPosgres = new conexion();
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

    public boolean registrarEvento(evento evento, ArrayList<evento> eventosArray) {

        try {
            String SQL = "INSERT INTO evento(\"nombreevento\", \"descripcion\", \"fechainicio\", \"fechafinal\", \"img\", \"vip\", \"plan_a\", \"plan_b\", \"vipmg\") "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            Connection connection = this.connPosgres.getConection();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, evento.getNombreEvento());
            sentencia.setString(2, evento.getSinopsis());

            // Convertir java.util.Date a java.sql.Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            Date fechaInicio = (Date) evento.getFechaInicioVisible();
            sentencia.setDate(3, fechaInicio);
            
            Date fechaFinal = (Date) evento.getFechaFinalVisible();

            sentencia.setDate(4, fechaFinal);
            sentencia.setString(5, evento.getLinkImg());
            sentencia.setFloat(6, evento.getVIP());
            sentencia.setFloat(7, evento.getPantlaA());
            sentencia.setFloat(8, evento.getPlantaB());
            sentencia.setFloat(9, evento.getPrecioVIPMG());

            sentencia.executeUpdate();

            ResultSet ultimoEvento = this.ejecutarConsulta("SELECT codigoevento FROM public.evento ORDER BY codigoevento DESC LIMIT 1");

            while (ultimoEvento.next()) {
                int codigoEvento = ultimoEvento.getInt("codigoevento");
                String SQL2 = "INSERT INTO fechaevento(\"codigoevento\", \"fecha\", \"hora\") VALUES (?, ?, ?)";
                PreparedStatement sentencia2 = connection.prepareStatement(SQL2);
                for (evento evento2 : eventosArray) {
                    String hora = evento2.getHora();
                    String fecha = evento2.getFecha();

                    java.util.Date fechaconvertido = dateFormat.parse(fecha);
                    java.sql.Date fechaSQL = new java.sql.Date(fechaconvertido.getTime());

                    sentencia2.setInt(1, codigoEvento);
                    sentencia2.setDate(2, fechaSQL);
                    sentencia2.setString(3, hora);

                    sentencia2.executeUpdate();
                }
            }
            return true;

        } catch (Exception e) {
            System.err.println("Ocurrio un error al registrar la tarea.");
            System.err.println("Mensaje del error: " + e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }
    }
}
