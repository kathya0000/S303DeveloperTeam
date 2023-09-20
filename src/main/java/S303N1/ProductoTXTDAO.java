package S303N1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
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
                System.out.println("Línea leída: " + line);
                String[] data = line.split(",");
                if(!data[0].equals("Ticket")) {
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
                } else {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public List<Ticket> cargarTickets() {
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].equals("Ticket")) {

                    int numeroTicket = Integer.parseInt(data[1]);
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = formato.parse(data[2]);
                    java.sql.Date fechaTicket = new java.sql.Date(utilDate.getTime());
                    double totalVenta = Double.parseDouble(data[3]);

                    List<LineaTicket> lineas = new ArrayList<>();
                    for (int i = 4; i < data.length; i += 7) {
                        int numLinea = Integer.parseInt(data[i]);
                        String tipo = data[i + 1];
                        String nombreProducto = data[i + 2];
                        double precioProducto = Double.parseDouble(data[i + 3]);
                        double altura = Double.parseDouble(data[i + 4]);
                        String color = data[i + 5];
                        String tipoMaterial = data[i + 6];

                        Producto producto;
                        if (tipo.equals("Arbol")) {
                            producto = new Arbol(nombreProducto, precioProducto, altura);
                        } else if (tipo.equals("Flor")) {
                            producto = new Flor(nombreProducto, precioProducto, color);
                        } else if (tipo.equals("Decoracion")) {
                            producto = new Decoracion(nombreProducto, precioProducto, tipoMaterial);
                        } else {
                            throw new IllegalArgumentException("Tipo de producto desconocido: " + tipo);
                        }

                        LineaTicket linea = new LineaTicket(numLinea, producto);
                        lineas.add(linea);
                    }

                    Ticket ticket = new Ticket(numeroTicket, fechaTicket, totalVenta, lineas);
                    tickets.add(ticket);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return tickets;
    }


    @Override
    public void guardarProductosYTickets(List<Producto> productos, List<Ticket> tickets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

            for (Producto producto : productos) {
                String tipo = "";

                if (producto instanceof Arbol) {
                    tipo = "Arbol";
                    double altura = ((Arbol) producto).getAltura();
                    writer.write(tipo + "," + producto.getNombre() + "," + producto.getPrecio() + "," + altura);
                } else if (producto instanceof Flor) {
                    tipo = "Flor";
                    String color = ((Flor) producto).getColor();
                    writer.write(tipo + "," + producto.getNombre() + "," + producto.getPrecio() + "," + color);
                } else if (producto instanceof Decoracion) {
                    tipo = "Decoracion";
                    String tipoMaterial = ((Decoracion) producto).getTipoMaterial();
                    writer.write(tipo + "," + producto.getNombre() + "," + producto.getPrecio() + "," + tipoMaterial);
                }

                writer.newLine();
            }

            for (Ticket ticket : tickets) {
                int numeroTicket = ticket.getNumeroTicket();
                Date fechaTicket = ticket.getFechaTicket();
                double totalVenta = ticket.getTotalVenta();

                StringBuilder sb = new StringBuilder();

                sb.append(numeroTicket).append(",").
                    append(fechaTicket).append(",").
                    append(totalVenta).append(",");

                for (LineaTicket linea : ticket.getLineas()) {
                    Producto producto = linea.getProducto();
                    String tipo = "";

                    if (producto instanceof Arbol) {
                        tipo = "Arbol";
                        sb.append(linea.getNumLinea()).append(",")
                                .append(tipo).append(",")
                                .append(producto.getNombre()).append(",")
                                .append(producto.getPrecio()).append(",")
                                .append(((Arbol) producto).getAltura()).append(",")
                                .append("").append(",")
                                .append("").append(",")
                                .append("");
                    } else if (producto instanceof Flor) {
                        tipo = "Flor";
                        sb.append(linea.getNumLinea()).append(",")
                                .append(tipo).append(",")
                                .append(producto.getNombre()).append(",")
                                .append(producto.getPrecio()).append(",")
                                .append("0.0").append(",")
                                .append(((Flor) producto).getColor()).append(",")
                                .append("").append(",")
                                .append("");
                    } else if (producto instanceof Decoracion) {
                        tipo = "Decoracion";
                        sb.append(linea.getNumLinea()).append(",")
                                .append(tipo).append(",")
                                .append(producto.getNombre()).append(",")
                                .append(producto.getPrecio()).append(",")
                                .append("0.0").append(",")
                                .append("").append(",")
                                .append(((Decoracion) producto).getTipoMaterial()).append(",")
                                .append("");
                    }
                }
                writer.write("Ticket," + sb.toString());
                writer.newLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }




}




