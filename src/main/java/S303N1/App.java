package S303N1;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class App {

    private static Floristeria floristeria;

    //   COMPROBAMOS SI HAY DATOS PREVIOS DE LA MISMA FLORISTERIA (METODO STATIC)
    private static String obtenerNombreArchivoPrevio(String directorio, String nombreFloristeria) {
        File folder = new File(directorio);
        File[] archivos = folder.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".txt")) {
                    String nombreArchivo = archivo.getName().replace(".txt", "");
                    if (nombreArchivo.equalsIgnoreCase(nombreFloristeria)) {
                        return archivo.getName();    //devuelve el nombre del txt sólo si hay datos previos
                    }
                }
            }
        }
        return null;
    }

    //    ****************************** MAIN ******************************

    public static void main(String[] args) {

        //   [ABRIMOS FLORISTERIA Y CARGAMOS SUS DATOS] ó [CREAMOS FLORISTERIA NUEVA]

        System.out.println("\nEn primer lugar, vamos a abrir el negocio de floristería.");
        Scanner input = new Scanner(System.in);
        System.out.println("Nombre de la floristería: ");
        String nombreFloristeria = input.nextLine();
        Floristeria floristeria = new Floristeria(nombreFloristeria);

        // Directorio donde se encuentran los archivos de persistencia
        String directorioPersistencia = ".";

        // Obtener el nombre del archivo previo
        String nombreArchivoPrevio = obtenerNombreArchivoPrevio(directorioPersistencia, nombreFloristeria);

        if (nombreArchivoPrevio != null) {

            //CASO 1 DE 2: LA FLORISTERIA YA TIENE DATOS: LOS CARGAMOS EN EL CATALOGO
            System.out.println("Existen datos anteriores de " + nombreArchivoPrevio);
            System.out.println("         cargando datos previos...");
            ProductoDAO productoDao = new ProductoTXTDAO(floristeria.getNombre());
            List<Producto> productos = productoDao.cargarProductos();
            floristeria.setCatalogo(productos);


        } else {

            //CASO 2 DE 2: FLORISTERIA NUEVA: MENÚ "INICIAL" DE ENTRADA DE LOS PRIMEROS DATOS
            ProductoDAO productoDao = new ProductoTXTDAO(floristeria.getNombre());
            boolean out = false;
            do {
                System.out.println("\nMenú inicial nueva floristería:\n" +
                        "\n 1 - Añadir instancia arbol" +
                        "\n 2 - Añadir instancia flor" +
                        "\n 0 - Seguir a menú principal (sólo tras haber entrado datos)");
                int opcionMenu0 = input.nextInt();

                switch (opcionMenu0) {
                    case 0:
                        if (floristeria.getCatalogo().isEmpty()) {
                            System.out.println("Antes de seguir debes haber introducido algún dato");
                        } else {
                            out = true;
                        }
                        break;
                    case 1:
                        input.nextLine();
                        System.out.println("Nombre del arbol: ");
                        String nombreArbol = input.nextLine();
                        System.out.println("Precio (EUR) del arbol '" + nombreArbol + "'");
                        double precioArbol = input.nextDouble();
                        System.out.println("Altura (mts) del arbol '" + nombreArbol + "'");
                        double alturaArbol = input.nextDouble();
                        input.nextLine();
                        FabricaProducto fabricaArbol = new FabricaArbol(nombreArbol, precioArbol, alturaArbol);
                        Producto arbol = fabricaArbol.crearProducto();
                        floristeria.agregarProducto(arbol);
                        break;
                    case 2:
                        input.nextLine();
                        System.out.println("Nombre de la flor: ");
                        String nombreFlor = input.nextLine();
                        System.out.println("Precio (EUR) de la flor '" + nombreFlor + "'");
                        double precioFlor = input.nextDouble();
                        input.nextLine();
                        System.out.println("Color de la flor '" + nombreFlor + "'");
                        String alturaFlor = input.nextLine();
                        FabricaProducto fabricaFlor = new FabricaFlor(nombreFlor, precioFlor, alturaFlor);
                        Producto flor = fabricaFlor.crearProducto();
                        floristeria.agregarProducto(flor);
                        break;
                    case 3:
                        input.nextLine();
                        System.out.println("Nombre de la decoración: ");
                        String nombreDeco = input.nextLine();
                        System.out.println("Precio (EUR) de la decoración '" + nombreDeco + "'");
                        double precioDeco = input.nextDouble();
                        input.nextLine();
                        System.out.println("Tipo de material de '" + nombreDeco + "'");
                        String tipoMaterial = input.nextLine();
                        FabricaProducto fabricaDecoracion = new FabricaDecoracion(nombreDeco, precioDeco, tipoMaterial);
                        Producto decoracion = fabricaDecoracion.crearProducto();
                        floristeria.agregarProducto(decoracion);
                        break;
                    default:
                        System.out.println("Inténtalo de nuevo");
                }

            } while (!out);
        }

        //MENÚ PRINCIPAL. AL SALIR, GUARDAREMOS LOS DATOS EN [NOMBRE_FLORISTERIA].TXT

        //    AHORA YA PODEMOS TRABAJAR NORMALMENTE

        boolean salir = false;
        do {
            System.out.println("Escoger una opcion:\n"
                    + "1 - Añadir nueva instancia arbol\n"
                    + "2 - Añadir nueva instancia flor \n"
                    + "3 - Añadir nueva instancia decoración\n"
                    + "4 - Listado de stock (instancias añadidas - eliminadas\n"
                    + "5 - Retirar instancia arbol (venta ficticia\n"
                    + "6 - Retirar instancia flor (venta ficticia)\n"
                    + "7 - Retirar instancia decoración (venta ficticia)\n"
                    + "8 - Valor de existencias\n"
                    + "9 - Registrar una venta e imprimir ticket\n"
                    + "10 - Listado histórico de tickets\n"
                    + "11 - Acumulado de ventas\n"
                    + "0 - Salir\n");

            int opcionMenu = input.nextInt();

            switch (opcionMenu) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    agregarArbol(input);
                    break;
                case 2:
                    agregarFlor(input);
                    break;
                case 3:
                    agregarDecoracion(input);
                    break;
                case 4:
                    mostrarStock();
                    break;
                case 5:
                    retirarArbol(input);
                    break;
                case 6:
                    retirarFlor(input);
                    break;
                case 7:
                    retirarDecoracion(input);
                    break;
                case 8:
                    mostrarValorExistencias();
                    break;
                case 9:
                    registrarVenta(input);
                    break;
                case 10:
                    mostrarHistoricoTickets();
                    break;
                case 11:
                    mostrarAcumuladoVentas();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (!salir);
        input.close();
    }

    private static void agregarArbol(Scanner input) {
        input.nextLine();
        System.out.println("Nombre del árbol: ");
        String nombreArbol = input.nextLine();
        System.out.println("Precio (EUR) del árbol '" + nombreArbol + "'");
        double precioArbol = input.nextDouble();
        System.out.println("Altura (mts) del árbol '" + nombreArbol + "'");
        double alturaArbol = input.nextDouble();
        input.nextLine();
        FabricaProducto fabricaArbol = new FabricaArbol(nombreArbol, precioArbol, alturaArbol);
        Producto arbol = fabricaArbol.crearProducto();
        floristeria.agregarProducto(arbol);
    }

    private static void agregarFlor(Scanner input) {
        input.nextLine();
        System.out.println("Nombre de la flor: ");
        String nombreFlor = input.nextLine();
        System.out.println("Precio (EUR) de la flor '" + nombreFlor + "'");
        double precioFlor = input.nextDouble();
        input.nextLine();
        System.out.println("Color de la flor '" + nombreFlor + "'");
        String colorFlor = input.nextLine();
        FabricaProducto fabricaFlor = new FabricaFlor(nombreFlor, precioFlor, colorFlor);
        Producto flor = fabricaFlor.crearProducto();
        floristeria.agregarProducto(flor);
    }

    private static void agregarDecoracion(Scanner input) {
        input.nextLine();
        System.out.println("Nombre de la decoración: ");
        String nombreDeco = input.nextLine();
        System.out.println("Precio (EUR) de la decoración '" + nombreDeco + "'");
        double precioDeco = input.nextDouble();
        input.nextLine();
        System.out.println("Tipo de material de '" + nombreDeco + "'");
        String tipoMaterial = input.nextLine();
        FabricaProducto fabricaDecoracion = new FabricaDecoracion(nombreDeco, precioDeco, tipoMaterial);
        Producto decoracion = fabricaDecoracion.crearProducto();
        floristeria.agregarProducto(decoracion);
    }

    private static void mostrarStock() {
        floristeria.mostrarCatalogo();
    }

    private static void retirarArbol(Scanner input) {
        input.nextLine();
        System.out.println("Nombre del árbol a eliminar: ");
        String nombreArbolAEliminar = input.nextLine();
        floristeria.retirarProducto(nombreArbolAEliminar);
    }

    private static void retirarFlor(Scanner input) {
        input.nextLine();
        System.out.println("Nombre de la flor a eliminar: ");
        String nombreFlorAEliminar = input.nextLine();
        floristeria.retirarProducto(nombreFlorAEliminar);
    }

    private static void retirarDecoracion(Scanner input) {
        input.nextLine();
        System.out.println("Nombre de la decoración a eliminar: ");
        String nombreDecorAEliminar = input.nextLine();
        floristeria.retirarProducto(nombreDecorAEliminar);
    }

    private static void mostrarValorExistencias() {
        System.out.println("Valor de existencias: " + floristeria.calcularValorExistencias() + " EUR");
    }

    private static void registrarVenta(Scanner input) {
        Ticket ticket = floristeria.generarTicket();
        floristeria.registrarVenta(ticket);
        System.out.println("Venta registrada correctamente. Aquí está tu ticket:\n");
        System.out.println(ticket.toString());
    }

    private static void mostrarHistoricoTickets() {
        System.out.println("Listado histórico de tickets:");
        floristeria.mostrarTickets();
    }

    private static void mostrarAcumuladoVentas() {
        System.out.println("Acumulado de ventas: " + floristeria.calcularVentasTotal() + " EUR");
    }
}
