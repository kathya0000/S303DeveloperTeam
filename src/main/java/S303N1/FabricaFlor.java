package S303N1;

public class FabricaFlor implements FabricaProducto {
    @Override
    public Producto crearProducto() {
        // Lógica para crear y configurar un objeto Flor
        String nombre = "Flor";
        double precio = 0.0;
        String color = "";
        return new Flor(nombre, precio, color);
    }
}