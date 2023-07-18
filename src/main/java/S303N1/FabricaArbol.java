package S303N1;

import java.util.Scanner;

public class FabricaArbol implements FabricaProducto {
    private String nombreArbol;
    private double precioArbol;
    private double alturaArbol;
    public FabricaArbol(String nombreArbol,double precioArbol, double alturaArbol){
        this.nombreArbol = nombreArbol;
        this.precioArbol = precioArbol;
        this.alturaArbol = alturaArbol;
    }
    @Override
    public Producto crearProducto() {
        return new Arbol(nombreArbol, precioArbol, alturaArbol);
    }

}
