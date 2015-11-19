/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.EntradaDao;
import Entidad.Entrada;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class EntradaDaoImpl implements EntradaDao{
     Conexion cn = Conexion.getInstance();
    @Override
    public boolean agregarEntrada(Entrada entrada) {
 boolean flat = false;
        Statement st = null;
        String query = "Insert into persona values ('"+entrada.getId_Entrada()+"','"+entrada.getIgv()+"','"+entrada.getDescuento()+
                                                        "','"+entrada.getSerie()+"','"+entrada.getFecha()+
                                                        "','"+entrada.getId_proveedor()+"','"+entrada.getNumero_comp()+"','"+entrada.getId_comprobante()
                                                      +"','"+entrada.getEstado()+"','"+entrada.getId_usuario()+"','"+entrada.getId_forma_pago()+
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
    public boolean actualizarEntrada(Entrada entrada) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE entrada SET id_entrada= '"+entrada.getId_Entrada()+
                       "', igv = '"+entrada.getIgv()+
                       "', descuento = '"+entrada.getDescuento()+
                       "', serie = '"+entrada.getSerie()+
                       "', fecha = '"+entrada.getFecha()+
                       "', id_proveedor = '"+entrada.getId_proveedor()+
                        "', numero_comp = '"+entrada.getNumero_comp()+
                       "', id_comprobante = '"+entrada.getEstado()+
                       "', estado = '"+entrada.getEstado()+
                        "', id_usuario = '"+entrada.getId_usuario()+
                         "', id_forma_pago = '"+entrada.getId_forma_pago()+
                       "' WHERE id_entrada = "+entrada.getId_Entrada();
        
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
    public boolean eliminarEntrada(int id_entrada) {
        boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM entrada WHERE id_entrada="+id_entrada+";";
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
        return flat;        }

    @Override
    public List<Entrada> listarEntrada() {
        List<Entrada> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Entrada entrada = null;
        String query = "Select id_entrada, igv, descuento,serie,fecha,id_proveedor,numero_comp,id_comprobante, estado,id_usuario,id_forma_pago FROM entrada";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                entrada = new Entrada();
                entrada.setId_Entrada(rs.getInt("id_entrada"));
                entrada.setIgv(rs.getInt("igv"));
                entrada.setDescuento(rs.getInt("descuento"));
                entrada.setSerie(rs.getInt("serie"));
                entrada.setFecha(rs.getString("fecha"));
                entrada.setId_proveedor(rs.getInt("id_proveedor"));
                entrada.setNumero_comp(rs.getInt("numero_comp"));
                entrada.setId_comprobante(rs.getInt("id_comprobante"));
                entrada.setEstado(rs.getInt("estado"));
                entrada.setId_usuario(rs.getInt("id_usuario"));
                entrada.setId_forma_pago(rs.getInt("id_forma_pago"));
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
        return Lista;      }
    
}
