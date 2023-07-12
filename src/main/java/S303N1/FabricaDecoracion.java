package S303N1;

public class FabricaDecoracion implements FabricaProducto {
    @Override
    public Producto crearProducto() {
        // Lógica para crear y configurar un objeto Decoracion
        String nombre = "Decoración";
        double precio = 0.0;
        String tipoMaterial = "";

        return new Decoracion(nombre, precio, tipoMaterial);
    }
}
