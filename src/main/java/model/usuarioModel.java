package model;

import java.sql.*;

import clases.usuario;

public class usuarioModel {

    private conexion connPosgres;
    public usuarioModel(){
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

    public boolean registrar(usuario usuario){

        try {
            String SQL = "INSERT INTO usuario(\"usuario\",\"clave\",\"codigorol\",\"codigoestado\",\"correo\") "
                    + "VALUES (?,?,?,?,?)";

            Connection connection = this.connPosgres.getConection();

            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, usuario.getUsuario() );
            sentencia.setString(2, usuario.getPassword());
            sentencia.setInt(3, usuario.getRol());
            sentencia.setInt(4,usuario.getEstado());
            sentencia.setString(5,usuario.getCorreo());

            sentencia.executeUpdate();
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
