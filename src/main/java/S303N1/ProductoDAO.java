package S303N1;

import java.util.List;

public interface ProductoDAO {
    List<Producto> cargarProductos();
    List<Ticket> cargarTickets();
    void guardarProductosYTickets(List<Producto> productos, List<Ticket> tickets);
}
