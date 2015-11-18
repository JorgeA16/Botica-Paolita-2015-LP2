package Dao;

import Entidad.Producto;
import Entidad.Seccion;
import Entidad.Unidad_Medida;
import java.util.List;


public interface ProductoDao {
    public boolean agregarProducto(Producto producto);
    public boolean actualizarProducto(Producto producto);
    public boolean eliminarProducto(int id_producto);
    public List<Producto> listarProducto(String buscar);
    public List<Unidad_Medida> listaruni_med();
    public List<Seccion> listarseccion();
    public List<Producto> filtrarProducto(String nombre_producto);
    public long contarProducto(int nombre_producto);
    public Producto buscarProductoID(int id_producto);
}
