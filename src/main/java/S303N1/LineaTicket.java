package S303N1;

public class LineaTicket {

    private Producto producto;
    private int cantidad;
    private double precio;

    public LineaTicket(Producto producto, int cantidad, double precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    //Getter
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getImporte() {
        double importe = 0;
        return importe;
    }
}
