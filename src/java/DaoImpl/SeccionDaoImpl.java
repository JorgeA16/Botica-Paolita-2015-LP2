/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.SeccionDao;
import Entidad.Seccion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class SeccionDaoImpl implements SeccionDao{
    Conexion cn = Conexion.getInstance();
    @Override
    public boolean agregarSeccion(Seccion seccion) {
boolean flat = false;
        Statement st = null;
        String query = "Insert into seccion values ('"+seccion.getId_seccion()+"','"+seccion.getEstado()+"','"+seccion.getId_categoria()+"')";
                                                        
                                                      
                                                        
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.conexion().setAutoCommit(false);
            cn.guardar();
            flat = true;
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
            e.printStackTrace();
            flat = false;
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
        return flat;      }

    @Override
    public boolean actualizarSeccion(Seccion seccion) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE seccion SET id_seccion= '"+seccion.getId_seccion()+
                       "', estado = '"+seccion.getEstado()+
                       "', id_categoria= '"+seccion.getId_categoria()+
                      "' WHERE id_seccion= "+seccion.getId_seccion();
        
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
        return flat;       }

    @Override
    public boolean eliminarSeccion(int id_seccion) {
boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM seccion WHERE id_seccion="+id_seccion+";";
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
        return flat;      }

    @Override
    public List<Seccion> listarSeccion() {
List<Seccion> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Seccion seccion = null;
        String query = "Select id_seccion, estado, id_categoria FROM seccion";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                seccion = new Seccion();
                seccion.setId_seccion(rs.getInt("id_seccion"));
                seccion.setEstado(rs.getInt("estado"));
                seccion.setId_categoria(rs.getInt("id_categoria"));
                Lista.add(seccion);
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
