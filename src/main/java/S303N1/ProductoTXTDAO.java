package S303N1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ProductoTXTDAO implements ProductoDAO {
    private String nombreArchivo;
    public ProductoTXTDAO(String nombreArchivo){
        this.nombreArchivo = nombreArchivo + ".txt";
    }

    @Override
    public List<Producto> cargarProductos() {

        List<Producto> productos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String tipo = data[0];
                String nombre = data[1];
                double precio = Double.parseDouble(data[2]);

                if (tipo.equals("Arbol")) {
                    double altura = Double.parseDouble(data[3]);
                    productos.add(new Arbol(nombre, precio, altura));
                } else if (tipo.equals("Flor")) {
                    String color = data[3];
                    productos.add(new Flor(nombre, precio, color));
                } else if (tipo.equals("Decoracion")) {
                    String tipoMaterial = data[3];
                    productos.add(new Decoracion(nombre, precio, tipoMaterial));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public void guardarProductos(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Producto producto : productos) {
                String tipo =   producto instanceof Arbol ? "Arbol" :
                                producto instanceof Flor ? "Flor" :
                                producto instanceof Decoracion ? "Decoracion" :
                                "";

                String nombre = producto.getNombre();
                double precio = producto.getPrecio();

                if (producto instanceof Arbol) {
                    double altura = ((Arbol) producto).getAltura();
                    writer.write(tipo + "," + nombre + "," + precio + "," + altura);
                } else if (producto instanceof Flor) {
                    String color = ((Flor) producto).getColor();
                    writer.write(tipo + "," + nombre + "," + precio + "," + color);
                } else if (producto instanceof Decoracion) {
                    String tipoMaterial = ((Decoracion) producto).getTipoMaterial();
                    writer.write(tipo + "," + nombre + "," + precio + "," + tipoMaterial);
                }

                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



