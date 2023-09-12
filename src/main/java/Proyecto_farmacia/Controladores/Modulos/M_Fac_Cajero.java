package Proyecto_farmacia.Controladores.Modulos;

public class M_Fac_Cajero {
    private String producto;
    private int cantidad;

    private String ci;
    private String nombre_factura;
    private int fecha_factura;
    private double subtotal_factura;
    private double IVA_factura;
    private double total_factura;

    private String cajero;

    public M_Fac_Cajero(String producto, int cantidad, String ci, String nombre_factura, int fecha_factura, double subtotal_factura, double IVA_factura, double total_factura, String cajero) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.ci = ci;
        this.nombre_factura = nombre_factura;
        this.fecha_factura = fecha_factura;
        this.subtotal_factura = subtotal_factura;
        this.IVA_factura = IVA_factura;
        this.total_factura = total_factura;
        this.cajero = cajero;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre_factura() {
        return nombre_factura;
    }

    public void setNombre_factura(String nombre_factura) {
        this.nombre_factura = nombre_factura;
    }

    public int getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(int fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public double getSubtotal_factura() {
        return subtotal_factura;
    }

    public void setSubtotal_factura(double subtotal_factura) {
        this.subtotal_factura = subtotal_factura;
    }

    public double getIVA_factura() {
        return IVA_factura;
    }

    public void setIVA_factura(double IVA_factura) {
        this.IVA_factura = IVA_factura;
    }

    public double getTotal_factura() {
        return total_factura;
    }

    public void setTotal_factura(double total_factura) {
        this.total_factura = total_factura;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

}
