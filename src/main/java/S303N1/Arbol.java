package S303N1;

public class Arbol extends Producto {
    private double altura;

    public Arbol(String nombre, double precio, double altura) {
        super(nombre, precio);
        this.altura = altura;
    }

    // Getter y setter
    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
