/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.CategoriaDao;
import Entidad.Categoria;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class CategoriaDaoImpl implements CategoriaDao{
    Conexion cn = Conexion.getInstance();
    
    
    @Override
    public boolean agregarCategoria(Categoria categoria) {
 boolean flat = false;
        Statement st = null;
        String query = "Insert into categoria values ('"+categoria.getId_categoria()+"','"+
                                                       categoria.getNombre_categoria()+
                                                       "','"+categoria.getEstado()+"')";
                                                                                                                
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.conexion().setAutoCommit(false);
            cn.guardar();
            flat = true;
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
            e.printStackTrace();
            flat = true;
            try {
            cn.restablecer();
            } catch (Exception ex) {
            }
        }finally{
            if (cn.conexion() != null) {
                try {
                    cn.restablecer();
                } catch (Exception e) {
                }
            }
        }
        return flat;    }

    @Override
    public boolean actualizarCategoria(Categoria categoria) {
  boolean flat = false;
        Statement st = null;
        String query = "UPDATE categoria SET id_categoria= '"+categoria.getId_categoria()+
                       "', nombre_categoria = '"+categoria.getNombre_categoria()+
                       "', estado = '"+categoria.getEstado()+
                       "' WHERE id_categoria = "+categoria.getId_categoria();
        
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.guardar();
//            cn.cerrar();
            flat = true;
        } catch (Exception e) {
            cn.restablecer();
//            cn.cerrar();
            System.out.println("ERROR: "+e.getMessage());
        }finally{
            if (cn.conexion() != null) {
//                cn.cerrar();
            }
        }
        return flat;    }

    @Override
    public boolean eliminarCategoria(int id_categoria) {

    boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM categoria WHERE id_categoria="+id_categoria+";";
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.guardar();
//            cn.cerrar();
            flat = true;
        } catch (Exception e) {
            cn.restablecer();
//            cn.cerrar();
            System.out.println("ERROR: "+e.getMessage());
        }finally{
            if (cn.conexion() != null) {
//                cn.cerrar();
            }
        }
        return flat;
    }
    
    
    

    @Override
    public List<Categoria> listarCategoria() {
List<Categoria> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Categoria categoria = null;
        String query = "Select id_categoria, nombre_categoria, estado FROM categoria";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                categoria.setEstado(rs.getInt("estado"));
                Lista.add(categoria);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
            try {
//                cn.cerrar();
            } catch (Exception ex) {
            }
        }
        return Lista;    }
    
}
