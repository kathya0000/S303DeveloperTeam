package S303N1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Floristeria {
    private static Floristeria instance;
    private String nombre;
    private List<Producto> catalogo;
    private List<Ticket> tickets;
    private Floristeria(String nombre) {
        this.nombre = nombre;
        this.catalogo = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }
    //Getter
    public static Floristeria getInstance(String nombre) {
        if (instance == null) {
            instance = new Floristeria(nombre);
        }
        return instance;
    }
    //Setters



    // Métodos para gestionar el catálogo y las ventas aquí
    //recibe un objeto Producto y lo agrega al catálogo de la floristería.
    public void agregarProducto(Producto producto) {
        catalogo.add(producto);
    }

    //recibe un objeto Producto y lo retira del catálogo de la floristería.
    public void retirarProducto(Producto producto) {
        catalogo.remove(producto);
    }

    //muestra por consola el catálogo de la floristería, mostrando el nombre y el precio de cada producto
    public void mostrarCatalogo() {
        System.out.println("Catálogo de la floristería:");
        for (Producto producto : catalogo) {
            System.out.println(producto.getNombre() + " - Precio: $" + producto.getPrecio());
        }
    }

    //genera un nuevo ticket de compra. Aquí debes implementar la lógica para solicitar al usuario los productos que desea comprar y calcular el total de la compra.
    public Ticket generarTicket() {
        int numeroTicket;
        numeroTicket = tickets.size() + 1;
        Date fechaCompra = new Date();
        List<Producto> productosComprados = new ArrayList<>();
        double totalCompra = 0.0;
        //solicitar al usuario los productos que desea comprar
        //.....

        // Calcular el total de la compra
        for (Producto producto : productosComprados) {
            totalCompra += producto.getPrecio();
        }

        return new Ticket(numeroTicket, fechaCompra, productosComprados, totalCompra);
    }

    // recibe un objeto Ticket y lo registra en la lista de tickets de la floristería.
    public void registrarVenta(Ticket ticket) {
        tickets.add(ticket);
    }

    //muestra por consola el stock de la floristería, es decir, la cantidad de cada producto en el catálogo
    public void mostrarStock() {
        System.out.println("Stock de la floristería:");
        for (Producto producto : catalogo) {
            int cantidad = calcularCantidadProducto(producto);
            System.out.println(producto.getNombre() + " - Cantidad: " + cantidad);
        }
    }

    //método auxiliar que calcula la cantidad de un producto específico en los tickets de venta.
    private int calcularCantidadProducto(Producto producto) {
        int cantidad = 0;
        for (Ticket ticket : tickets) {
            for (Producto productoTicket : ticket.getProductos()) {
                if (productoTicket.equals(producto)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    //muestra por consola el valor total de la floristería, sumando el total de todas las compras registradas en los tickets.
    public void mostrarValorTotal() {
        double valorTotal = 0.0;
        for (Ticket ticket : tickets) {
            valorTotal += ticket.getTotalCompra();
        }
        System.out.println("Valor total de la floristería: $" + valorTotal);
    }

    // Getters y setters
    // ...
}


