package S303N1;

public class LineaTicket {
    /*implementación, la clase LineaTicket representa una línea de un ticket de compra y puede contener información como
    el producto, la cantidad y el importe de esa línea*/
    //Atributos
    private Producto producto;//producto: representa el producto de la línea de ticket.
    private int cantidad;//cantidad: representa la cantidad de ese producto en la línea de ticket.
    private double importe;//importe: representa el importe total de esa línea de ticket, calculado multiplicando el precio del producto por la cantidad.

    //Metodo Constructor
    //recibe el producto y la cantidad, y calcula automáticamente el importe.
    public LineaTicket(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getImporte() {
        return importe;
    }
    //Setter
    // setPrecio permite actualizar el precio del producto y recalcular el importe de la línea de ticket.
    public void setPrecio(int nuevoPrecio) {
        producto.setPrecio(nuevoPrecio);
        calcularImporte();
    }

    private void calcularImporte() {
        importe = producto.getPrecio() * cantidad;
    }
    //devuelve una representación en forma de cadena de la línea de ticket, mostrando el nombre del producto, la cantidad y el importe.
    @Override
    public String toString() {
        return "Producto: " + producto.getNombre() +
                ", Cantidad: " + cantidad +
                ", Importe: " + importe;
    }

    public double getSubtotal() {
        return getSubtotal();
    }

    public int calcularSubtotal() {
        return 0;
    }
