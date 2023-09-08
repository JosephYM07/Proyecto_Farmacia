package Proyecto_farmacia.Controladores.Modulos;

public class M_Admin {
    private String nombreUsuario;
    private String contrasena;

    public M_Admin(String nombreUsuario, String contrasena) {
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
