/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Eleazar
 */
public class asientos {
     private int codigo;
    private int columna;
    private String fila;
    private boolean activo;
    private String lado;
    private int codigoSeccion;
     private seccion section = new seccion();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String gila) {
        this.fila = gila;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean estaActivo() {
        return activo;
    }
    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public int getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(int codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }
    
     public seccion getSeccion() {
        return section;
    }
     
     public void setSeccion(seccion section) {
        this.section = section;
    }
}
