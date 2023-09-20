package S303N1;
import java.io.Serializable;
import java.util.ArrayList;

public class LineaTicket implements Serializable {
    private int numLinea = 0;
    private Producto producto;
    private String nombreProducto;
    private double precioProducto;
    private String tipo;
    private double altura;
    private String color;
    private String material;



    public LineaTicket(int numLinea, Producto producto) {
        this.numLinea = numLinea;
        this.producto = producto;
    }

    /*
    //constructor para reconstruir lineas de tickets cargados via txt
    public LineaTicket(int numLinea, String tipo, String nombreProducto, double precioProducto, double altura, String color, String material){
        this.numLinea = numLinea;
        this.tipo = tipo;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        if(tipo=="Arbol"){
            this.altura = altura;
            this.color = null;
            this.material = null;
        }else if(tipo=="Flor"){
            this.altura = 0.0;
            this.color = color;
            this.material = null;
        }else if(tipo=="Decoracion"){
            this.altura = 0.0;
            this.color = null;
            this.material = material;
        }else{
            tipo = null;
        }
    }
     */


    //Getters
    public int getNumLinea() { return numLinea; }
    public Producto getProducto() { return producto; }
    public double getPrecio() { return producto.getPrecio(); }

    //Setters
    public void setNumLinea(int numLinea){
        this.numLinea = numLinea;
    }


}
