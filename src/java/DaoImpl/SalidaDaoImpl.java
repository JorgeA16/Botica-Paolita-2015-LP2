/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.SalidaDao;
import Entidad.Salida;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class SalidaDaoImpl implements SalidaDao{
    Conexion cn = Conexion.getInstance();
    @Override
    public boolean agregarSalida(Salida salida) {
boolean flat = false;
        Statement st = null;
        String query = "Insert into salida values ('"+salida.getId_salida()+"','"+salida.getIgv()+"','"+salida.getDescuento()+
                                                        "','"+salida.getFecha()+"','"+salida.getId_cliente()+
                                                        "','"+salida.getHora()+"','"+salida.getId_usuario()+"','"+salida.getId_forma_pago()+"')";
                                                      
                                                        
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
    public boolean actualizarSalida(Salida salida) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE salida SET id_salida= '"+salida.getId_salida()+
                       "', igv = '"+salida.getIgv()+
                       "', decuento= '"+salida.getDescuento()+
                       "', fecha = '"+salida.getFecha()+
                       "', id_cliente = '"+salida.getId_cliente()+
                       "', hora = '"+salida.getHora()+
                        "', id_usuario = '"+salida.getId_usuario()+
                       "', id_forma_pago = '"+salida.getId_forma_pago()+
                      
                "' WHERE id_salida = "+salida.getId_salida();
        
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
        return flat;     }

    @Override
    public boolean eliminarSalida(int id_salida) {
boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM salida WHERE id_salida="+id_salida+";";
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
    public List<Salida> listarSalida() {
 List<Salida> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Salida salida = null;
        String query = "Select id_salida, igv, decuento, fecha, id_cliente, hora, id_usuario,id_forma_pago FROM salida";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                salida = new Salida();
                salida.setId_salida(rs.getInt("id_salida"));
                salida.setIgv(rs.getInt("igv"));
                salida.setDescuento(rs.getInt("descuento"));
                salida.setDescuento(rs.getInt("decuento"));
                salida.setFecha(rs.getString("fecha"));
                salida.setId_cliente(rs.getInt("id_cliente"));
                salida.setHora(rs.getString("hora"));
                salida.setId_usuario(rs.getInt("id_usuario"));
                salida.setId_forma_pago(rs.getInt("id_forma_pago"));
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
        return Lista;     }
    
}
