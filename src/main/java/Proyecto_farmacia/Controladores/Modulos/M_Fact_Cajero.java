package Proyecto_farmacia.Controladores.Modulos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Date;

public class M_Fact_Cajero {

    private int Id_Cajero;

    private int Id_Prod;

    private String Nombre_Producto;

    private int Cantidad_Producto;

    private String CI_Cliente;

    private String Nombre_Cliente;

    private String Desc_Producto;

    private Date Fecha_Factura;

    private int Subtotal;

    private int Precio;

    private int Iva;
    private int Total_Fac;

    public M_Fact_Cajero(int id_Cajero, int id_Prod, String nombre_Producto, int cantidad_Producto, String CI_Cliente, String nombre_Cliente, String desc_Producto, Date fecha_Factura, int subtotal, int precio, int iva, int total_Fac) {
        Id_Cajero = id_Cajero;
        Id_Prod = id_Prod;
        Nombre_Producto = nombre_Producto;
        Cantidad_Producto = cantidad_Producto;
        this.CI_Cliente = CI_Cliente;
        Nombre_Cliente = nombre_Cliente;
        Desc_Producto = desc_Producto;
        Fecha_Factura = fecha_Factura;
        Subtotal = subtotal;
        Precio = precio;
        Iva = iva;
        Total_Fac = total_Fac;
    }

    public M_Fact_Cajero(String nomProd, String stoProd, String valVentProd) {
    }

    public int getId_Cajero() {
        return Id_Cajero;
    }

    public void setId_Cajero(int id_Cajero) {
        Id_Cajero = id_Cajero;
    }

    public int getId_Prod() {
        return Id_Prod;
    }

    public void setId_Prod(int id_Prod) {
        Id_Prod = id_Prod;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        Nombre_Producto = nombre_Producto;
    }

    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    public void setCantidad_Producto(int cantidad_Producto) {
        Cantidad_Producto = cantidad_Producto;
    }

    public String getCI_Cliente() {
        return CI_Cliente;
    }

    public void setCI_Cliente(String CI_Cliente) {
        this.CI_Cliente = CI_Cliente;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public void setNombre_Cliente(String nombre_Cliente) {
        Nombre_Cliente = nombre_Cliente;
    }

    public String getDesc_Producto() {
        return Desc_Producto;
    }

    public void setDesc_Producto(String desc_Producto) {
        Desc_Producto = desc_Producto;
    }

    public Date getFecha_Factura() {
        return Fecha_Factura;
    }

    public void setFecha_Factura(Date fecha_Factura) {
        Fecha_Factura = fecha_Factura;
    }

    public int getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(int subtotal) {
        Subtotal = subtotal;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public int getIva() {
        return Iva;
    }

    public void setIva(int iva) {
        Iva = iva;
    }

    public int getTotal_Fac() {
        return Total_Fac;
    }

    public void setTotal_Fac(int total_Fac) {
        Total_Fac = total_Fac;
    }
}