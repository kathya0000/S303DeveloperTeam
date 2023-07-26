package S303N1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Ticket {
    private static int contadorTickets = 0;
    private int numeroTicket;
    private Date fechaTicket;
    private List<LineaTicket> lineas;
    private List<Producto> productosVendidos;
    private double totalVenta;
    private Producto productoSeleccionado;

    public Ticket() {
        contadorTickets++;
        this.fechaTicket = new Date(System.currentTimeMillis());
        this.numeroTicket = contadorTickets;
        this.lineas = new ArrayList<>();
        this.totalVenta = 0.0;
        this.productosVendidos = new ArrayList<Producto>();
    }

    //Getter
    public int getNumeroTicket() {  return numeroTicket;    }
    public Date getFechaTicket() {  return fechaTicket;    }
    public double getTotalVenta() { return totalVenta;  }
    public ArrayList<LineaTicket> getLineas(){  return (ArrayList<LineaTicket>) lineas;  }


    public void agregarLinea(int numeroTicket, Producto productoSeleccionado) {
        this.numeroTicket = numeroTicket;
        int numLinea = this.lineas.size()+1;
        LineaTicket nuevaLinea = new LineaTicket(numLinea, productoSeleccionado);
        lineas.add(nuevaLinea);
        this.totalVenta += nuevaLinea.getProducto().getPrecio();
    }


    // leer
    public void verTicket() {
        //Verifica si el numTicket ya esta hecho
        System.out.println("Número de ticket: " + numeroTicket);
        System.out.println(" Fecha de venta: " + fechaTicket);
        System.out.println(" Artículos:");
        for (LineaTicket linea : lineas) {
            System.out.println("   " + linea.getNumLinea() + " - 1ud." + linea.getProducto().getNombre() + " - €" + linea.getPrecio());
        }
        System.out.println(" Importe total: €" + totalVenta + " \n");
    }

    //Calcular Total tickets
    private void calcularTotalVenta () {
        totalVenta = 0.0;
        for (LineaTicket linea : lineas) {
            totalVenta += linea.getPrecio();
        }
    }

}
