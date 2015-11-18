/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.ComprobanteDao;
import Entidad.Comprobante;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class ComprobanteDaoImpl implements ComprobanteDao{

        Conexion cn = Conexion.getInstance();

    
    @Override
    public boolean agregarComprobante(Comprobante comprobante) {
boolean flat = false;
        Statement st = null;
        String query = "Insert into comprobante values ('"+comprobante.getId_comprobante()+"','"+comprobante.getNombre_comprobante()+
                                                        "','"+comprobante.getAbreviatura()+"','"+comprobante.getEstado()+"')";
                                                                                                                         
                                                        
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
    public boolean actualizarComprobante(Comprobante comprobante) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE comprobante SET id_comprobante= '"+comprobante.getId_comprobante()+
                       "', nombre_comprobante = '"+comprobante.getNombre_comprobante()+
                       "', abreviatura = '"+comprobante.getAbreviatura()+
                       "', estado = '"+comprobante.getEstado()+
                       "' WHERE id_comprobante = "+comprobante.getId_comprobante();
        
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
    public boolean eliminarComprobante(int id_comprobante) {
boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM comprobante WHERE id_comprobante="+id_comprobante+";";
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
    public List<Comprobante> listarComprobante() {
List<Comprobante> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Comprobante comprobante = null;
        String query = "Select id_comprobante, nombre_comprobante, abreviatura, estado FROM comprobante";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                comprobante = new Comprobante();
                comprobante.setId_comprobante(rs.getInt("id_comprobante"));
                comprobante.setNombre_comprobante(rs.getString("nombre_comprobante"));
                comprobante.setAbreviatura(rs.getString("abreviatura"));
                comprobante.setEstado(rs.getInt("estado"));
                Lista.add(comprobante);
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
