package Proyecto_farmacia.Controladores.Modulos;

public class M_Cajero {
    private String nombreUsuario;
    private String contrasena;

    public M_Cajero(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }
}
