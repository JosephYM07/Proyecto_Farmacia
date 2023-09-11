package Proyecto_farmacia.Controladores.Modulos;

public class M_Crud_Cajero {
    private int id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String correo;
    private String contacto;
    private String usuario;
    private String contraseña;

    public M_Crud_Cajero(int id, String nombres, String apellidos, String direccion,
                         String correo, String contacto, String usuario, String contraseña) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo = correo;
        this.contacto = contacto;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public M_Crud_Cajero() {
        // Constructor sin argumentos
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
