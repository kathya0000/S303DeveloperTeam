package S303N1;

public class LineaTicket {
    private int numLinea = 0;
    private Producto producto;

    public LineaTicket(int numLinea, Producto producto) {
        this.numLinea = numLinea;
        this.producto = producto;
    }
    //Getter
    public int getNumLinea() { return numLinea; }
    public Producto getProducto() {
        return producto;
    }
    public double getPrecio() {
        return producto.getPrecio();
    }

}
