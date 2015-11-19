/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.SalidaDetalleDao;
import Entidad.Salida_Detalle;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class SalidaDetalleDaoImpl implements SalidaDetalleDao{
      Conexion cn = Conexion.getInstance();
    
    @Override
    public boolean agregarSalidaDetalle(Salida_Detalle salida_detalle) {
boolean flat = false;
        Statement st = null;
        String query = "Insert into salida_detalle values ('"+salida_detalle.getId_lote_interno()+"','"+salida_detalle.getId_salida()+"','"+salida_detalle.getCantidad()+
                                                        "','"+salida_detalle.getPrecio()+"','"+salida_detalle.getCantidad_uni()+
                                                        "','"+salida_detalle.getId_unidad()+"','"+salida_detalle.getDescuento()+"')";
                                                        
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
        return flat;     }

    @Override
    public boolean actualizarSalidaDetalle(Salida_Detalle salida_detalle) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE salida_detalle SET id_lote_interno= '"+salida_detalle.getId_lote_interno()+
                       "', id_salida = '"+salida_detalle.getId_salida()+
                       "', catidad= '"+salida_detalle.getCantidad()+
                       "', precio = '"+salida_detalle.getPrecio()+
                       "', cantidad_uni = '"+salida_detalle.getCantidad_uni()+
                       "', id_unidad = '"+salida_detalle.getId_unidad()+
                        "', descuento = '"+salida_detalle.getDescuento()+
                       
                "' WHERE id_lote_interno = "+salida_detalle.getId_lote_interno();
        
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
    public boolean eliminarSalidaDetalle(int id_lote_interno) {
        boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM salida_detalle WHERE id_lote_interno="+id_lote_interno+";";
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
    public List<Salida_Detalle> listarSalidaDetalle() {
List<Salida_Detalle> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Salida_Detalle salida = null;
        String query = "Select id_lote_interno, id_salida, catidad,precio,cantidad_uni,id_unidad,descuento FROM salida_detalle";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                salida = new Salida_Detalle();
                salida.setId_lote_interno(rs.getInt("id_lote_interno"));
                salida.setId_salida(rs.getInt("id_salida"));
                salida.setCantidad_uni(rs.getInt("catidad"));
                salida.setPrecio(rs.getInt("precio"));
                salida.setCantidad_uni(rs.getInt("cantidad_uni"));
                salida.setId_unidad(rs.getInt("id_unidad"));
                salida.setDescuento(rs.getInt("descuento"));
                
                Lista.add(salida);
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
