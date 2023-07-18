package S303N1;

import java.util.Scanner;

public class App {
        public static void main(String[] args) {

            //   ABRIMOS FLORISTERIA

            System.out.println("\nEn primer lugar, vamos a abrir el negocio de floristería.");
            Scanner input = new Scanner(System.in);
            System.out.println("Nombre de la floristería: ");
            String nombreFloristeria = input.nextLine();
            // Aplicamos patron Singleton para no crear repetidas floristerias
            Floristeria floristeria = Floristeria.getInstance(nombreFloristeria);
            System.out.println("La Floristería '" + nombreFloristeria + "' ha sido creada");

            //    ENTRAMOS PRIMER INVENTARIO PARA PODER TRABAJAR

            boolean out = false;
            do{
                System.out.println("\nMenú:\n");
                System.out.println(" 1 - Añadir instancia arbol");
                System.out.println(" 2 - Añadir instancia flor");
                System.out.println(" 3 - Añadir instancia decoración");
                System.out.println(" 0 - Seguir a menú principal");
                int opcionMenu0 = input.nextInt();

                switch (opcionMenu0) {
                    case 0:
                        out = true;
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

            //    AHORA YA PODEMOS TRABAJAR NORMALMENTE

            boolean salir = false;
            do {
                System.out.println("\nMenu:\n");
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
                System.out.println(" 0 - Salir");

                int opcionMenu = input.nextInt();

                switch (opcionMenu) {
                    case 0:
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




