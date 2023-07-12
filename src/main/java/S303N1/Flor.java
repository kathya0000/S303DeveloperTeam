package S303N1;

public class Flor extends Producto {
    private String color;

    public Flor(String nombre, double precio, String color) {
        super(nombre, precio);
        this.color = color;
    }

    // Getter y setter
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
