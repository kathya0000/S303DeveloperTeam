package S303N1;
    public class App {
        public static void main(String[] args) {


            Floristeria floristeria = Floristeria.getInstance("Mi Floristería");

            // Agregar productos al catálogo
            FabricaProducto fabricaArbol= new FabricaArbol();
            Producto arbol = fabricaArbol.crearProducto();
            floristeria.agregarProducto(arbol);

            FabricaProducto fabricaFlor= new FabricaFlor();
            Producto flor = fabricaFlor.crearProducto();
            floristeria.agregarProducto(flor);

            FabricaProducto fabricaDecoracion = new FabricaDecoracion();
            Producto decoracion = fabricaDecoracion.crearProducto();
            floristeria.agregarProducto(decoracion);

            // Mostrar el catálogo de la floristería
            floristeria.mostrarCatalogo();

            // Generar un ticket de compra
            Ticket ticket = floristeria.generarTicket();
            floristeria.registrarVenta(ticket);

            // Mostrar el stock y el valor total de la floristería
            floristeria.mostrarStock();
            floristeria.mostrarValorTotal();
        }
    }




