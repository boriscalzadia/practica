
package net.negociostecnologicos.dao;

import net.negociostecnologicos.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO extends DAO{
    
    //metodo
    public void registrar(Persona per) throws SQLException, Exception{ //recibe un obetjo tipo persona
     
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO persona (nombre, sexo) values (?,?)");
            st.setString (1, per.getNombre());
            st.setString(2, per.getSexo());
            st.executeUpdate();
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        
    }
    
    //metodo listar
    public List<Persona> listar() throws Exception{
        
        //Creamos una lista de tipo persona
        List<Persona> lista;
        //Necesitamos una variable Resulset para guardar la data
        ResultSet rs;
        
      try{
          this.Conectar();
          PreparedStatement st = this.getCn().prepareCall("SELECT codigo, nombre,sexo FROM persona ");
          rs = st.executeQuery();
          
          //Recorremos el resulset con el while e instanciamos la lista de tipo persona
          lista = new ArrayList();
          while (rs.next()){
              Persona per = new Persona();
              per.setCodigo(rs.getInt("codigo"));
              per.setNombre(rs.getString("nombre"));
              per.setSexo(rs.getString("sexo"));
             
              lista.add(per);
          }
          
      } catch (Exception e) {
          throw e;
      }finally{
          this.Cerrar();
          
      }
      return lista;
    }
    //Termina el metodo para Listar
    
    //Metodo para Mostrar en pantalla el elemento seleccionado
    public Persona leerID (Persona per) throws Exception{
        //Creamos una variable de tipo persona
        Persona pers = null;
        //Necesistamos un Resultset
        ResultSet rs;
    try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, sexo FROM persona WHERE codigo= ?");
            st.setInt (1, per.getCodigo());
            rs= st.executeQuery();
            
            while (rs.next()){
                pers = new Persona();
                pers.setCodigo(rs.getInt("codigo"));
                pers.setNombre(rs.getString("nombre"));
                pers.setSexo(rs.getString("sexo"));
                
            }
            
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        return pers;
        
    }
    
    //Metodo que podamos modificar de acuerdo al ID
    public void modificar(Persona per) throws SQLException, Exception{ //recibe un obetjo tipo persona
     
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE  persona SET nombre=?, sexo=? WHERE codigo=?");
            st.setString (1, per.getNombre());
            st.setString(2, per.getSexo());
            st.setInt(3, per.getCodigo());
            st.executeUpdate();
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        
    }
    
    //Metodo que podamos eliminiar de acuerdo al ID
    public void eliminar(Persona per) throws SQLException, Exception{ //recibe un obetjo tipo persona
     
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM persona WHERE codigo=?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        
    }
   
}
