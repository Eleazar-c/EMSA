/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Objects;

/**
 *
 * @author Eleazar
 */
public class evento {

    private String fecha;

    private String hora;
    
    private String nombreEvento;
    private String sinopsis;
    
    private String fechaInicioVisible;
    private String fechaFinalVisible;
    private float precioVIPMG;
    private float VIP;
    private float PantlaA;
    private float PlantaB;

    private String linkImg;

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getFechaInicioVisible() {
        return fechaInicioVisible;
    }

    public void setFechaInicioVisible(String fechaInicioVisible) {
        this.fechaInicioVisible = fechaInicioVisible;
    }

    public String getFechaFinalVisible() {
        return fechaFinalVisible;
    }

    public void setFechaFinalVisible(String fechaFinalVisible) {
        this.fechaFinalVisible = fechaFinalVisible;
    }

    public float getPrecioVIPMG() {
        return precioVIPMG;
    }

    public void setPrecioVIPMG(float precioVIPMG) {
        this.precioVIPMG = precioVIPMG;
    }

    public float getVIP() {
        return VIP;
    }

    public void setVIP(float VIP) {
        this.VIP = VIP;
    }

    public float getPantlaA() {
        return PantlaA;
    }

    public void setPantlaA(float PantlaA) {
        this.PantlaA = PantlaA;
    }

    public float getPlantaB() {
        return PlantaB;
    }

    public void setPlantaB(float PlantaB) {
        this.PlantaB = PlantaB;
    }
    
    
     /*public evento(String Fecha, String Hora) {
        this.fecha = Fecha;
        this.hora = Hora;
    }*/

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final evento other = (evento) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return Objects.equals(this.hora, other.hora);
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
