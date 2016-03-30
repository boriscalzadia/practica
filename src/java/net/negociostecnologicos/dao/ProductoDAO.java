
package net.negociostecnologicos.dao;

import net.negociostecnologicos.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends DAO{
    
    //metodo
    public void registrar(Producto pro) throws SQLException, Exception{ //recibe un obetjo tipo producto
     
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO producto (nombre, precio) values (?,?)");
            st.setString (1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.executeUpdate();
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        
    }
    
    //metodo listar
    public List<Producto> listar() throws Exception{
        
        //Creamos una lista de tipo producto
        List<Producto> lista;
        //Necesitamos una variable Resulset para guardar la data
        ResultSet rs;
        
      try{
          this.Conectar();
          PreparedStatement st = this.getCn().prepareCall("SELECT codigo, nombre, precio FROM producto ");
          rs = st.executeQuery();
          
          //Recorremos el resulset con el while e instanciamos la lista de tipo producto
          lista = new ArrayList();
          while (rs.next()){
              Producto pro = new Producto();
              pro.setCodigo(rs.getInt("codigo"));
              pro.setNombre(rs.getString("nombre"));
              pro.setPrecio(rs.getDouble("precio"));
             
              lista.add(pro);
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
    public Producto leerID (Producto per) throws Exception{
        //Creamos una variable de tipo producto
        Producto pers = null;
        //Necesistamos un Resultset
        ResultSet rs;
    try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, precio FROM producto WHERE codigo= ?");
            st.setInt (1, per.getCodigo());
            rs= st.executeQuery();
            
            while (rs.next()){
                pers = new Producto();
                pers.setCodigo(rs.getInt("codigo"));
                pers.setNombre(rs.getString("nombre"));
                pers.setPrecio(rs.getDouble("precio"));
                
            }
            
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        return pers;
        
    }
    
    //Metodo que podamos modificar de acuerdo al ID
    public void modificar(Producto per) throws SQLException, Exception{ //recibe un obetjo tipo producto
     
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE  producto SET nombre=?, precio=? WHERE codigo=?");
            st.setString (1, per.getNombre());
            st.setDouble(2, per.getPrecio());
            st.setInt(3, per.getCodigo());
            st.executeUpdate();
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        
    }
    
    //Metodo que podamos eliminiar de acuerdo al ID
    public void eliminar(Producto per) throws SQLException, Exception{ //recibe un obetjo tipo producto
     
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM producto WHERE codigo=?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
            
        } catch (Exception e){
          throw e;
        } finally {
                 this.Cerrar();
        }
        
    }
   
}
