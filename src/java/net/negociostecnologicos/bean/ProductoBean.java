
package net.negociostecnologicos.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.negociostecnologicos.dao.ProductoDAO;
import net.negociostecnologicos.model.Producto;

@ManagedBean  //Anotacion 
//@RequestScoped //Ambito lo cambiamos a view por el comportamiento ajax
@ViewScoped

public class ProductoBean {
    
    private Producto producto = new Producto();//Acceso a sus atributos y poder guardarlos
//Declaramos una Variable de tipo Lista
    private List <Producto> lstProductos;

    public String getAccion() {
        return accion;
    }

//PAra cambiar el valor al boton
    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    private String accion;
    

    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
    }
    
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    //Creamos un nuevo metodo para evaluar si es modificar o registrar 
    public void operar() throws Exception{
        switch (accion){
            case "Registrar":
                this.registrar();
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }
    
    public void limpiar(){
        this.producto.setCodigo(0);
        this.producto.setNombre("");
        this.producto.setPrecio(0);
    }
    
    private void registrar () throws Exception{ //Funcion para registrar, invoca los metodos para del patron de acceso DAO
        ProductoDAO dao;//Declara la Clase DAO
        
        try {
            dao = new ProductoDAO();
            dao.registrar(producto);
            this.listar(); //para resfrestar el datatable luego del ingreso
        }catch (Exception e){
            throw e;
        }
        
        
    }
    
     private void modificar () throws Exception{ //Funcion para modificar, invoca los metodos para del patron de acceso DAO
        ProductoDAO dao;//Declara la Clase DAO
        
        try {
            dao = new ProductoDAO();
            dao.modificar(producto);
            this.listar(); //para resfrestar el datatable luego de la modificacion
        }catch (Exception e){
            throw e;
        }
        
        
         }
      public void eliminar (Producto per) throws Exception{ //Funcion para modificar, invoca los metodos para del patron de acceso DAO
        ProductoDAO dao;//Declara la Clase DAO
        
        try {
            dao = new ProductoDAO();
            dao.eliminar(per);
            this.listar(); //para resfrestar el datatable luego de la modificacion
        }catch (Exception e){
            throw e;
        }
        
        
         }
    
      
     
    //Llamando al metodo listar del boton para motrar en el datatable
    public void listar () throws Exception{ //Funcion para registrar, invoca los metodos para del patron de acceso DAO
        ProductoDAO dao;//Declara la Clase DAO
        
        try {
            dao = new ProductoDAO();
            lstProductos = dao.listar();
        }catch (Exception e){
            throw e;
        }
        
        
         }
    
    //Leer id con parametro de entrada del datatable
    public void leerID(Producto per) throws Exception{
        ProductoDAO dao;
        Producto temp;
        try {
            dao = new ProductoDAO();
            temp=dao.leerID(per);
            //Variable temporal 
            if (temp != null){
            this.producto = temp; //asigno el valor global si es difernte de nulo y la variable tendra los valores correcto
            this.accion = "Modificar";
        }
        } catch (Exception e) {
            throw e;
        }
    }
    
    }
