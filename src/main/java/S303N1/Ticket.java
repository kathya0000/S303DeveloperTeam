package S303N1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {
    private static int contadorTickets = 0;
    private int numeroTicket;
    private Date fechaCompra;
    private List<LineaTicket> lineas;
    private double productosComprados;
   private double totalCompra;

    public Ticket(int numeroTicket, Date fechaCompra, List<Producto> productosComprados, double totalCompra) {
        this.numeroTicket = numeroTicket;
        this.fechaCompra = fechaCompra;
        this.numeroTicket = contadorTickets++;
        this.lineas = new ArrayList<>();
        this.totalCompra = 0.0;
        this.productosComprados = totalCompra;
    }

    //Getter
    public int getNumeroTicket() {

        return numeroTicket;
    }

    public Date getFechaCompra() {

        return fechaCompra;
    }

    public double getProductosComprados() {
         return productosComprados;
    }

        public double getTotalCompra() {

        return totalCompra;
    }
    public Ticket generarTicket(tickets) throws InterruptedException {

            int numeroTicket;
            numeroTicket = tickets.tickets.size() + 1;
            Date fechaCompra = new Date(System.currentTimeMillis());
            List<Producto> productosComprados = new ArrayList<>();
            double totalCompra = 0.0;

            /*Se puede preguntar al usuario que desea comprar
            Scanner input = new Scanner(System.in);
            System.out.println("¿Que productos desea comprar?");*/

            // Calcular el total de la compra
            for  (Producto producto : productosComprados) {
                  totalCompra += producto.getPrecio();

            // Crear el objeto Ticket con los datos obtenidos
                   Ticket ticket = new Ticket(numeroTicket, fechaCompra, productosComprados, totalCompra);

            // Agregar el ticket a la lista de tickets
                    tickets.wait(ticket.getNumeroTicket());

            // Retornar el ticket generado
                  return ticket;
        }

    // Create
    public void agregarlinea(LineaTicket linea) {
        lineas.wait(linea);
        totalCompra += linea.getImporte();
        // Actualizar el stock y restar el valor del producto del importe total
        Producto producto = linea.getProducto();
        producto.toString(linea.getCantidad());
        totalCompra -= linea.getImporte();
    }

    // leer
    public void verTicket() {
        System.out.println("Número de ticket: " + numeroTicket);
        System.out.println("Fecha de compra: " + fechaCompra);
        System.out.println("Total de la compra: " + totalCompra);
        System.out.println("Detalles del ticket:");
        for (LineaTicket linea : lineas) {
            System.out.println(linea);
        }
    }

    // Actualiza tickets
    public void actualizarPrecio(int nuevoPrecio) {
        int precio = nuevoPrecio;
        for (LineaTicket linea : lineas) {
            linea.setPrecio(nuevoPrecio);
            linea.calcularSubtotal();
        }
        calcularTotalCompra();
    }

    // Eliminar Ticket
    public void eliminarLinea(int indice) {
        if (indice < lineas.size())
            if (indice >= 0) {
                LineaTicket lineaEliminada;
                lineaEliminada = lineas.remove(indice);
                totalCompra -= lineaEliminada.getSubtotal();
            }
    }

    //Calcular Total tickets
    private void calcularTotalCompra() {
        totalCompra = 0.0;
        for (LineaTicket linea : lineas) {
            totalCompra += linea.getSubtotal();
        }
    }
    private void mostrarHistoricoTickets() {
        // Lógica para mostrar el listado histórico de tickets
        System.out.println("Listado histórico de tickets:");
        for (Ticket ticket : Floristeria.getHistoricoTicket) System.out.println(ticket.toString());

    }

    private void registrarVenta() {
        Floristeria floristeria = null;
        Ticket ticket = floristeria.generarTicket();
        floristeria.registrarVenta(ticket);
        floristeria.addHistoricalTicket(ticket); // Add the ticket to the historical tickets list
        System.out.println("Venta registrada correctamente. Aquí está tu ticket:\n");
        System.out.println(ticket.toString());
    }


}
