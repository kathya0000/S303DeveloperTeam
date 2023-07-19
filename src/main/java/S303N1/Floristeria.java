package S303N1;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Floristeria {
    private String nombre;
    private static String nombreArchivo;    //fichero donde se guardarán los datos, con el nombre de la floristería
    private List<Producto> catalogo;
    private List<Ticket> tickets;
    public Floristeria(String nombre) {
        this.nombre = nombre;
        this.catalogo = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    // Métodos para gestionar el catálogo y las ventas :


    //recibe un objeto Producto y lo agrega al catálogo de la floristería.
    public void agregarProducto(Producto producto) {
        catalogo.add(producto);
    }

    //recibe un objeto Producto y lo retira del catálogo de la floristería.
    public void retirarProducto(String nombreProducto) {
        try {
            // Buscar el producto en el catálogo
            Iterator<Producto> iter = catalogo.iterator();
            boolean encontrado = false;

            while (iter.hasNext()) {
                Producto producto = iter.next();
                if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                    iter.remove();
                    System.out.println("Producto eliminado: " + nombreProducto);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("El producto no está en el catálogo.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al retirar el producto: " + e.getMessage());
        }
    }

    //muestra por consola el catálogo de la floristería, mostrando todos los atributos de cada producto
    public void mostrarCatalogo() {
        System.out.println("Catálogo de la floristería:\n");
        for (Producto producto : catalogo) {
            System.out.print(producto.getNombre() + " - Precio: EUR " + producto.getPrecio());
            if (producto instanceof Arbol) {
                System.out.println(" - Altura ARBOL: " + ((Arbol) producto).getAltura() + "mts.");
            }
            if (producto instanceof Flor) {
                System.out.println(" - Color FLOR: " + ((Flor) producto).getColor());
            }
            if (producto instanceof Decoracion) {
                System.out.println(" - Material DECORACION: " + ((Decoracion) producto).getTipoMaterial());
            }
        }
    }

    //genera un nuevo ticket de compra. Aquí debes implementar la lógica para solicitar al usuario los productos que desea comprar y calcular el total de la compra.
    public Ticket generarTicket() {
        int numeroTicket;
        numeroTicket = tickets.size() + 1;
        Date fechaCompra = new Date(System.currentTimeMillis());
        List<Producto> productosComprados = new ArrayList<>();
        double totalCompra = 0.0;
        //solicitar al usuario los productos que desea comprar
        //.....

        // Calcular el total de la compra
        for (Producto producto : productosComprados) {
            totalCompra += producto.getPrecio();
        }

        return new Ticket(fechaCompra);
    }

    // recibe un objeto Ticket y lo registra en la lista de tickets de la floristería.
    public void registrarVenta(Ticket ticket) {
        tickets.add(ticket);
    }

    //muestra por consola el stock de la floristería, es decir, la cantidad de cada producto en el catálogo
    public void mostrarStock() {
        System.out.println("Stock de la floristería:\n");
        int cantidad = 0;
        int cantidadArboles = 0;
        int cantidadFlores = 0;
        int cantidadDecoraciones = 0;
        double valorInventario = 0.0;
        double valorArboles = 0.0;
        double valorFlores = 0.0;
        double valorDecoraciones = 0.0;
        for (Producto producto : catalogo) {
            if(producto instanceof Arbol) {
                cantidadArboles++;
                valorArboles = valorArboles + producto.getPrecio();
                System.out.println("1 un.ARBOL: " + producto.getNombre() + " valorado en: " + producto.getPrecio() + "EUR");
            }
            if(producto instanceof Flor) {
                cantidadFlores++;
                valorFlores = valorFlores + producto.getPrecio();
                System.out.println("1 un.FLOR: " + producto.getNombre() + " valorada en: " + producto.getPrecio() + "EUR");
            }
            if(producto instanceof Decoracion) {
                cantidadDecoraciones++;
                valorDecoraciones = valorDecoraciones + producto.getPrecio();
                System.out.println("1 un.DECORACION: " + producto.getNombre() + " valorada en: " + producto.getPrecio() + "EUR");
            }
        }
        cantidad = cantidadArboles + cantidadFlores + cantidadDecoraciones;
        valorInventario = valorArboles + valorFlores + valorDecoraciones;
        System.out.println("\n          " + "CANTIDAD  " + "IMPORTE(EUR)");
        System.out.println("ARBOLES       " + cantidadArboles + "     " + valorArboles);
        System.out.println("FLORES        " + cantidadFlores + "     " + valorFlores);
        System.out.println("DECORACIONES  " + cantidadDecoraciones + "     " + valorDecoraciones);
        System.out.println("--------------------------------");
        System.out.println("TOTAL         " + cantidad + "     " + valorInventario);
    }

    //método auxiliar que calcula la cantidad de un producto específico en los tickets de venta.
    private int calcularCantidadProducto(Producto producto) {
        int cantidad = 0;
        for (Ticket ticket : tickets) {
            for (LineaTicket lineaTicket : ticket.getLineas()){
                if (lineaTicket.getProducto().equals(producto)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    //muestra por consola el total de todas las ventas registradas en los tickets.
    public void mostrarVentasTotal() {
        double valorTotal = 0.0;
        for (Ticket ticket : tickets) {
            valorTotal += ticket.getTotalCompra();
        }
        System.out.println("Ventas totales de la floristería: EUR " + valorTotal);
    }

    // Getters y setters

    public String getNombre(){
        return nombre;
    }
    public String getNombreArchivo(){
        return nombreArchivo;
    }

    public List<Producto> getCatalogo() {
        return catalogo;
    }
    public void setCatalogo(List<Producto> catalogoAnterior){
        this.catalogo = catalogoAnterior;
    }
}


