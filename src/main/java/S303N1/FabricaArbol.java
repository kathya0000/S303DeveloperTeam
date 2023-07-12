package S303N1;

public class FabricaArbol implements FabricaProducto {
    @Override
    public Producto crearProducto() {
        // Lógica para crear y configurar un objeto Arbol
        String nombre = "Árbol";
        double precio = 0.0;
        double altura = 0.0;

        return new Arbol(nombre, precio, altura);
    }
}
