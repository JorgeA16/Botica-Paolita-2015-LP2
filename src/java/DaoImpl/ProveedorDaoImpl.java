/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.ProveedorDao;
import Entidad.Proveedor;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class ProveedorDaoImpl implements ProveedorDao{
    Conexion cn = Conexion.getInstance();
    @Override
    public boolean agregarProveedor(Proveedor proveedor) {

  boolean flat = false;
        Statement st = null;
        String query = "Insert into persona values ('"+proveedor.getId_proveedor()+"','"+proveedor.getNombre()+"','"+proveedor.getRuc()+
                                                        "','"+proveedor.getCelular()+"','"+proveedor.getDireccion()+"')";
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.conexion().setAutoCommit(false);
            cn.guardar();
            cn.cerrar();
            flat = true;
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
            e.printStackTrace();
            flat = true;
            try {
            cn.restablecer();
            cn.cerrar();
            } catch (Exception ex) {
            }
        }finally{
            if (cn.conexion() != null) {
                try {
                    cn.restablecer();
                    cn.cerrar();
                } catch (Exception e) {
                }
            }
        }
        return flat; 
    
    
    }

    @Override
    public boolean actualizarProveedor(Proveedor proveedor) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE proveedor SET id_proveedor= '"+proveedor.getId_proveedor()+
                       "', nombre = '"+proveedor.getNombre()+
                       "', apepat = '"+proveedor.getRuc()+
                       "', apemat = '"+proveedor.getCelular()+
                       "', celular = '"+proveedor.getDireccion()+
                       "' WHERE id_proveedor = "+proveedor.getId_proveedor();
        
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.guardar();
            cn.cerrar();
            flat = true;
        } catch (Exception e) {
            cn.restablecer();
            cn.cerrar();
            System.out.println("ERROR: "+e.getMessage());
        }finally{
            if (cn.conexion() != null) {
                cn.cerrar();
            }
        }
        return flat;
        }

    @Override
    public boolean eliminarProveedor(int id_proveedor) {
boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM proveedor WHERE id_proveedor="+id_proveedor+";";
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.guardar();
            cn.cerrar();
            flat = true;
        } catch (Exception e) {
            cn.restablecer();
            cn.cerrar();
            System.out.println("ERROR: "+e.getMessage());
        }finally{
            if (cn.conexion() != null) {
                cn.cerrar();
            }
        }
        return flat;     }

    @Override
    public List<Proveedor> listarProveedor() {
List<Proveedor> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Proveedor proveedor = null;
        String query = "Select id_proveedor, nombre, ruc, celular, direccion FROM Proveedor";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
            cn.cerrar();
            while (rs.next()) {                
                proveedor = new Proveedor();
                proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setRuc(rs.getInt("ruc"));
                proveedor.setCelular(rs.getInt("celular"));
                proveedor.setDireccion(rs.getString("direccion"));
                Lista.add(proveedor);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
            try {
                cn.cerrar();
            } catch (Exception ex) {
            }
        }
        return Lista;     }

    @Override
    public Proveedor buscarProveedorID(int id_proveedor) {
      Proveedor proveedor = null;
        String query = "SELECT * FROM proveedor WHERE id_proveedor= "+id_proveedor;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
            cn.cerrar();
            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setRuc(rs.getInt("ruc"));
                proveedor.setCelular(rs.getInt("celular"));
                proveedor.setDireccion(rs.getString("direccion"));
                
            }
        } catch (Exception e) {
            try {
                cn.cerrar();
            } catch (Exception ex) {
                System.out.println("ERROR: "+e.getMessage());
                ex.printStackTrace();
            }finally{
                if (cn.conexion() != null) {
                    cn.cerrar();
                }
            }
        }
        return proveedor;    }
    
}
