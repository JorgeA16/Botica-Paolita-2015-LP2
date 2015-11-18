package Dao;

import Entidad.Proveedor;
import java.util.List;


public interface ProveedorDao {
    public boolean agregarProveedor(Proveedor proveedor);
    public boolean actualizarProveedor(Proveedor proveedor);
    public boolean eliminarProveedor(int id_proveedor);
    public List<Proveedor> listarProveedor();
    public Proveedor buscarProveedorID(int id_proveedor);
}
