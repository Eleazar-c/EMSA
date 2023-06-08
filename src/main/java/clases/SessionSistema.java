package clases;

public class SessionSistema {

    private static String Usuario;
    private static String Clave;
    private static String nombre;
    private static String sistemaSession;

    private static int codigoUsuario;
    private static String correo;
    private static int codigoEstado;
    private static int codigoRol;
    
    
    public static String getUsuario() {
        return Usuario;
    }

    public static void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public static String getClave() {
        return Clave;
    }

    public static void setClave(String clave) {
        Clave = clave;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        SessionSistema.nombre = nombre;
    }

    public static String getSistemaSession() {
        return sistemaSession;
    }

    public static void setSistemaSession(String sistemaSession) {
        SessionSistema.sistemaSession = sistemaSession;
    }

    public static int getCodigoUsuario() {
        return codigoUsuario;
    }

    public static void setCodigoUsuario(int codigoUsuario) {
        SessionSistema.codigoUsuario = codigoUsuario;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        SessionSistema.correo = correo;
    }

    public static int getCodigoEstado() {
        return codigoEstado;
    }

    public static void setCodigoEstado(int codigoEstado) {
        SessionSistema.codigoEstado = codigoEstado;
    }

    public static int getCodigoRol() {
        return codigoRol;
    }

    public static void setCodigoRol(int codigoRol) {
        SessionSistema.codigoRol = codigoRol;
    }
}
