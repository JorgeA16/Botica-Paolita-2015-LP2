/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImpl;

import Conexion.Conexion;
import Dao.LaboratorioDao;
import Entidad.Laboratorio;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GATA
 */
public class LaboratorioDaoImpl implements LaboratorioDao{

    Conexion cn = Conexion.getInstance();
    @Override
    public boolean agregarLaboratorio(Laboratorio laboratorio) {
     boolean flat = false;
      Statement st=null;
      String query= "Insert into laboratorio values('"+laboratorio.getId_laboratorio()+"','"+laboratorio.getEstado()+"','"+
                                                       laboratorio.getNombre_laboratorio()+"')"; 
      
      
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
      
     
     
     
     
     
     return flat;
    
    }

    @Override
    public boolean actualizarLaboratorio(Laboratorio laboratorio) {
boolean flat = false;
        Statement st = null;
        String query = "UPDATE laboratorio SET id_laboratorio= '"+laboratorio.getId_laboratorio()+
                       "', estado = '"+laboratorio.getEstado()+
                       "', nombre_laboratorio= '"+laboratorio.getNombre_laboratorio()+
                      
                "' WHERE id_laboratorio = "+laboratorio.getId_laboratorio();
        
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
    public boolean eliminarLaboratorio(int id_laboratorio) {
 boolean flat = false;
        Statement st = null;
        String query = "DELETE FROM laboratorio WHERE id_laboratorio="+id_laboratorio+";";
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
    public List<Laboratorio> listarLaboratorio() {
        List<Laboratorio> Lista = null;
        Statement st = null;
        ResultSet rs = null;
        Laboratorio laboratorio = null;
        String query = "Select id_laboratorio, estado, nombre_laboratorio FROM laboratorio";
        try {
            Lista = new ArrayList<>();
            st = cn.conexion().createStatement();
            rs = st.executeQuery(query);
//            cn.cerrar();
            while (rs.next()) {                
                laboratorio = new Laboratorio();
                laboratorio.setId_laboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setEstado(rs.getInt("estado"));
                laboratorio.setNombre_laboratorio(rs.getString("nombre_laboratorio"));
                
                Lista.add(laboratorio);
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
