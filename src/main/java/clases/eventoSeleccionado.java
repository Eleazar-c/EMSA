/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Eleazar
 */
public class eventoSeleccionado {

    private static int CodigoEvento;
    private static String Fecha;
    private static String Hora;

    private static int Vip;
    private static int VipMG;
    private static int PlanA;
    private static int PlanB;
    private static Parametro parametroObj = new Parametro<String>();
    private static int totalvoletos;

    public static Parametro getParametroObj() {
        return parametroObj;
    }

    public static void setParametroObj(Parametro parametroObj) {
        eventoSeleccionado.parametroObj = parametroObj;
    }

    public static int getTotalvoletos() {
        return totalvoletos;
    }

    public static void setTotalvoletos(int totalvoletos) {
        eventoSeleccionado.totalvoletos = totalvoletos;
    }

    public static int getVip() {
        return Vip;
    }

    public static void setVip(int vip) {
        Vip = vip;
    }

    public static int getVipMG() {
        return VipMG;
    }

    public static void setVipMG(int vipMG) {
        VipMG = vipMG;
    }

    public static int getPlanA() {
        return PlanA;
    }

    public static void setPlanA(int planA) {
        PlanA = planA;
    }

    public static int getPlanB() {
        return PlanB;
    }

    public static void setPlanB(int planB) {
        PlanB = planB;
    }

    public static String getFecha() {
        return Fecha;
    }

    public static void setFecha(String Fecha) {
        eventoSeleccionado.Fecha = Fecha;
    }

    public static String getHora() {
        return Hora;
    }

    public static void setHora(String Hora) {
        eventoSeleccionado.Hora = Hora;
    }

    public static int getCodigoEvento() {
        return CodigoEvento;
    }

    public static void setCodigoEvento(int codigoEvento) {
        CodigoEvento = codigoEvento;
    }
}
