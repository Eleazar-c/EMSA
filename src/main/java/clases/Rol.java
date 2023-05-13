/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Eleazar
 */
public class Rol {


    private int codigo;
    private String nombre;

    public Rol(int codigo , String nombre ){
        this.codigo = codigo;
        this.nombre=nombre;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    // Sobreescribir el método toString() para que muestre el nombre del rol
    @Override
    public String toString() {
        return nombre;
    }
}
