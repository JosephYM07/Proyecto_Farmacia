package Proyecto_farmacia.Controladores.Modulos;

public class M_Crud_Productos {
    private int id_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private int stock_producto;
    private double valor_producto;
    private String proveedor_producto;
    private boolean iva_producto;

    public M_Crud_Productos(int id_producto, String nombre_producto, String descripcion_producto, int stock_producto, double valor_producto, String proveedor_producto, boolean iva_producto) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.stock_producto = stock_producto;
        this.valor_producto = valor_producto;
        this.proveedor_producto = proveedor_producto;
        this.iva_producto = iva_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public int getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(int stock_producto) {
        this.stock_producto = stock_producto;
    }

    public double getValor_producto() {
        return valor_producto;
    }

    public void setValor_producto(double valor_producto) {
        this.valor_producto = valor_producto;
    }

    public String getProveedor_producto() {
        return proveedor_producto;
    }

    public void setProveedor_producto(String proveedor_producto) {
        this.proveedor_producto = proveedor_producto;
    }

    public boolean isIva_producto() {
        return iva_producto;
    }

    public void setIva_producto(boolean iva_producto) {
        this.iva_producto = iva_producto;
    }
}
