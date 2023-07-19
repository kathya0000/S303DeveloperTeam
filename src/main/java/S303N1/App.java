package S303N1;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class App {
    //   COMPROBAMOS SI HAY DATOS PREVIOS DE LA MISMA FLORISTERIA
    private static String obtenerNombreArchivoPrevio(String directorio, String nombreFloristeria) {
        File folder = new File(directorio);
        File[] archivos = folder.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".txt")) {
                    String nombreArchivo = archivo.getName().replace(".txt", "");
                    if (nombreArchivo.equals(nombreFloristeria)) {
                        return archivo.getName();    //devuelve el nombre del txt sólo si hay datos previos
                    }
                }
            }
        }

        return null;
    }
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
                System.out.println("Existen datos anteriores de " + nombreArchivoPrevio);
                System.out.println("         cargando datos previos...");
                ProductoDAO productoDao = new ProductoTXTDAO(floristeria.getNombre());
                List<Producto> productos = productoDao.cargarProductos();
                floristeria.setCatalogo(productos);


            } else {

                ProductoDAO productoDao = new ProductoTXTDAO(floristeria.getNombre());
                boolean out = false;
                do{
                    System.out.println("\nMenú inicial nueva floristería:\n");
                    System.out.println(" 1 - Añadir instancia arbol");
                    System.out.println(" 2 - Añadir instancia flor");
                    System.out.println(" 3 - Añadir instancia decoración");
                    System.out.println(" 0 - Seguir a menú principal (sólo tras haber entrado datos)");
                    int opcionMenu0 = input.nextInt();

                    switch (opcionMenu0) {
                        case 0:
                            if(floristeria.getCatalogo().isEmpty()){
                                System.out.println("Antes de seguir debes haber introducido algún dato");
                            } else {
                                out = true;
                            }
                            break;
                        case 1:
                            input.nextLine();
                            System.out.println("Nombre del arbol: ");
                            String nombreArbol = input.nextLine();
                            System.out.println("Precio (EUR) del arbol '"+ nombreArbol +"'");
                            double precioArbol = input.nextDouble();
                            System.out.println("Altura (mts) del arbol '"+ nombreArbol +"'");
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
                            System.out.println("Precio (EUR) de la flor '"+ nombreFlor +"'");
                            double precioFlor = input.nextDouble();
                            input.nextLine();
                            System.out.println("Color de la flor '"+ nombreFlor +"'");
                            String alturaFlor = input.nextLine();
                            FabricaProducto fabricaFlor = new FabricaFlor(nombreFlor, precioFlor, alturaFlor);
                            Producto flor = fabricaFlor.crearProducto();
                            floristeria.agregarProducto(flor);
                            break;
                        case 3:
                            input.nextLine();
                            System.out.println("Nombre de la decoración: ");
                            String nombreDeco = input.nextLine();
                            System.out.println("Precio (EUR) de la decoración '"+ nombreDeco +"'");
                            double precioDeco = input.nextDouble();
                            input.nextLine();
                            System.out.println("Tipo de material de '"+ nombreDeco +"'");
                            String tipoMaterial = input.nextLine();
                            FabricaProducto fabricaDecoracion = new FabricaDecoracion(nombreDeco, precioDeco, tipoMaterial);
                            Producto decoracion = fabricaDecoracion.crearProducto();
                            floristeria.agregarProducto(decoracion);
                            break;
                        default:
                            System.out.println("Inténtalo de nuevo");
                    }

                } while(!out);
            }

            boolean salir = false;
            do {
                System.out.println("\nMenú Principal:\n");
                System.out.println(" 1 - Añadir nueva instancia arbol");
                System.out.println(" 2 - Añadir nueva instancia flor");
                System.out.println(" 3 - Añadir nueva instancia decoración");
                System.out.println(" 4 - Listado de stock (instancias añadidas - eliminadas)");
                System.out.println(" 5 - Retirar instancia arbol (venta ficticia)");
                System.out.println(" 6 - Retirar instancia flor (venta ficticia)");
                System.out.println(" 7 - Retirar instancia decoración (venta ficticia)");
                System.out.println(" 8 - Valor de existencias");
                System.out.println(" 9 - Registrar una venta e imprimir ticket");
                System.out.println("10 - Listado histórico de tickets");
                System.out.println("11 - Acumulado de ventas");
                System.out.println(" 0 - Guardar datos y SALIR");

                int opcionMenu = input.nextInt();

                switch (opcionMenu) {
                    case 0:
                        ProductoDAO dao = new ProductoTXTDAO(nombreFloristeria);
                        dao.guardarProductos(floristeria.getCatalogo());
                        salir = true;
                        break;
                    case 1:
                        input.nextLine();
                        System.out.println("Nombre del arbol: ");
                        String nombreArbol = input.nextLine();
                        System.out.println("Precio (EUR) del arbol '"+ nombreArbol +"'");
                        double precioArbol = input.nextDouble();
                        System.out.println("Altura (mts) del arbol '"+ nombreArbol +"'");
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
                        System.out.println("Precio (EUR) de la flor '"+ nombreFlor +"'");
                        double precioFlor = input.nextDouble();
                        input.nextLine();
                        System.out.println("Color de la flor '"+ nombreFlor +"'");
                        String alturaFlor = input.nextLine();
                        FabricaProducto fabricaFlor = new FabricaFlor(nombreFlor, precioFlor, alturaFlor);
                        Producto flor = fabricaFlor.crearProducto();
                        floristeria.agregarProducto(flor);
                        break;
                    case 3:
                        input.nextLine();
                        System.out.println("Nombre de la decoración: ");
                        String nombreDeco = input.nextLine();
                        System.out.println("Precio (EUR) de la decoración '"+ nombreDeco +"'");
                        double precioDeco = input.nextDouble();
                        input.nextLine();
                        System.out.println("Tipo de material de '"+ nombreDeco +"'");
                        String tipoMaterial = input.nextLine();
                        FabricaProducto fabricaDecoracion = new FabricaDecoracion(nombreDeco, precioDeco, tipoMaterial);
                        Producto decoracion = fabricaDecoracion.crearProducto();
                        floristeria.agregarProducto(decoracion);
                        break;
                    case 4:
                        floristeria.mostrarCatalogo();
                        break;
                    case 5:
                        input.nextLine();
                        System.out.println("Nombre del árbol a eliminar: ");
                        String nombreArbolAEliminar = input.nextLine();
                        floristeria.retirarProducto(nombreArbolAEliminar);
                        break;
                    case 6:
                        input.nextLine();
                        System.out.println("Nombre de la flor a eliminar: ");
                        String nombreFlorAEliminar = input.nextLine();
                        floristeria.retirarProducto(nombreFlorAEliminar);
                        break;
                    case 7:
                        input.nextLine();
                        System.out.println("Nombre de la decoración a eliminar: ");
                        String nombreDecorAEliminar = input.nextLine();
                        floristeria.retirarProducto(nombreDecorAEliminar);
                        break;
                    case 8:
                        floristeria.mostrarStock();
                        break;
                    case 9:

                        break;
                    case 10:
                        Ticket ticket = floristeria.generarTicket();
                        floristeria.registrarVenta(ticket);
                        break;
                    case 11:
                        floristeria.mostrarVentasTotal();
                        break;
                    case 12:

                        break;
                    default:
                        System.out.println("Inténtalo de nuevo");
                }
            } while (!salir);
            input.close();
        }
    }




