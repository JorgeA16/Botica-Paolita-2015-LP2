/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.ServicioDao;
import Entidad.Servicio;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class ServicioDaoImpl implements ServicioDao{
   Conexion cn = Conexion.getInstance();
    @Override
    public boolean agregarServicio(Servicio servicio) {
boolean flat = false;
        Statement st=null;
      String query= "Insert into servicio values('"+servicio.getId_servicio()+"','"+servicio.getNombre_servicio()+"','"+
                                                       servicio.getPrecio()+"')"; 
      
      
        try {
            st = cn.conexion().createStatement();
            st.executeUpdate(query);
            cn.conexion().setAutoCommit(false);
            cn.guardar();
            flat = true;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
            flat = false;
            try {
//                cn.cerrar();
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
    public boolean actualizarServicio(Servicio servicio) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE servicio SET id_servicio= '"+servicio.getId_servicio()+
                       "', nombre_servicio = '"+servicio.getNombre_servicio()+
                       "', precio= '"+servicio.getPrecio()+
                       "' WHERE id_servicio = "+servicio.getId_servicio();
        
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
    public boolean eliminarServicio(int id_servicio) {
 boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM servicio WHERE id_servicio="+id_servicio+";";
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
    public List<Servicio> listarServicio() {
List<Servicio> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Servicio servicio = null;
        String query = "Select id_lote_interno, id_salida, catidad,precio,cantidad_uni,id_unidad,descuento FROM salida_detalle";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                servicio = new Servicio();
                servicio.setId_servicio(rs.getInt("id_servicio"));
                servicio.setNombre_servicio(rs.getString("nombre_servicio"));
                servicio.setPrecio(rs.getInt("precio"));
                Lista.add(servicio);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
            try {
//                cn.cerrar();
            } catch (Exception ex) {
            }
        }
        return Lista;     }
    
}
