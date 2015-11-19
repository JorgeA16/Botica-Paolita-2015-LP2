/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.EntradaDetalleDao;
import Entidad.Entrada_Detalle;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class EntradaDetalleDaoImpl implements EntradaDetalleDao{
        Conexion cn = Conexion.getInstance();
    @Override
    public boolean agregarEntradaDetalle(Entrada_Detalle entrada_detalle) {
boolean flat = false;
        Statement st = null;
        String query = "Insert into entrada_detalle values ('"+entrada_detalle.getId_lote_interno()+"','"+entrada_detalle.getCantidad()+"','"+entrada_detalle.getPrecio_compra()+
                                                        "','"+entrada_detalle.getFecha_ven()+"','"+entrada_detalle.getEstado()+
                                                        "','"+entrada_detalle.getStock()+"','"+entrada_detalle.getId_laboratorio()+"','"+entrada_detalle.getId_entrada()
                                                      +"','"+entrada_detalle.getId_producto()+"','"+entrada_detalle.getId_unidad()+"','"+entrada_detalle.getCantidad_uni()+
                                                      "','"+entrada_detalle.getOrden()+"','"+entrada_detalle.getLote()+
                                                        "')";
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
    public boolean actualizarEntradaDetalle(Entrada_Detalle entrada_detalle) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE entrada_detalle SET id_lote_interno= '"+entrada_detalle.getId_lote_interno()+
                       "', cantidad = '"+entrada_detalle.getCantidad()+
                       "', precio_compra= '"+entrada_detalle.getPrecio_compra()+
                       "', fecha_ven = '"+entrada_detalle.getFecha_ven()+
                       "', estado = '"+entrada_detalle.getEstado()+
                       "', stock = '"+entrada_detalle.getStock()+
                        "', id_laboratorio = '"+entrada_detalle.getId_laboratorio()+
                       "', id_entrada = '"+entrada_detalle.getId_entrada()+
                       "', eid_producto = '"+entrada_detalle.getId_producto()+
                        "', id_unidad = '"+entrada_detalle.getId_unidad()+
                         "', cantidad_uni = '"+entrada_detalle.getCantidad_uni()+
                       "', orden = '"+entrada_detalle.getOrden()+
                       "', lote = '"+entrada_detalle.getLote()+
                "' WHERE id_lote_interno = "+entrada_detalle.getId_lote_interno();
        
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
    public boolean eliminarEntradaDetalle(int id_lote_interno) {

    boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM entrada WHERE id_entrada="+id_lote_interno+";";
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
    public List<Entrada_Detalle> listarEntradaDetalle() {
  List<Entrada_Detalle> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Entrada_Detalle entrada = null;
        String query = "Select id_entrada, igv, descuento,serie,fecha,id_proveedor,numero_comp,id_comprobante, estado,id_usuario,id_forma_pago FROM entrada";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                entrada = new Entrada_Detalle();
                entrada.setId_lote_interno(rs.getInt("id_lote_interno"));
                entrada.setCantidad(rs.getInt("cantidad"));
                entrada.setPrecio_compra(rs.getInt("precio_compra"));
                entrada.setFecha_ven(rs.getString("fecha_ven"));
                entrada.setEstado(rs.getInt("estado"));
                entrada.setStock(rs.getInt("stock"));
                entrada.setId_laboratorio(rs.getInt("id_laboratorio"));
                entrada.setId_entrada(rs.getInt("id_estrada"));
                entrada.setId_producto(rs.getInt("id_producto"));
                entrada.setId_unidad(rs.getInt("id_unidad"));
                entrada.setCantidad_uni(rs.getInt("cantidad_uni"));
                entrada.setLote(rs.getInt("lote"));
                Lista.add(entrada);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
            try {
//                cn.cerrar();
            } catch (Exception ex) {
            }
        }
        return Lista;        }
    
}
