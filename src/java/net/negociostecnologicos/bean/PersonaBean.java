
package net.negociostecnologicos.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.negociostecnologicos.dao.PersonaDAO;
import net.negociostecnologicos.model.Persona;

@ManagedBean  //Anotacion 
//@RequestScoped //Ambito lo cambiamos a view por el comportamiento ajax
@ViewScoped

public class PersonaBean {
    
    private Persona persona = new Persona();//Acceso a sus atributos y poder guardarlos
//Declaramos una Variable de tipo Lista
    private List <Persona> lstPersonas;

    public String getAccion() {
        return accion;
    }

//PAra cambiar el valor al boton
    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    private String accion;
    

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }
    
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        this.persona.setCodigo(0);
        this.persona.setNombre("");
        this.persona.setSexo("");
    }
    
    private void registrar () throws Exception{ //Funcion para registrar, invoca los metodos para del patron de acceso DAO
        PersonaDAO dao;//Declara la Clase DAO
        
        try {
            dao = new PersonaDAO();
            dao.registrar(persona);
            this.listar(); //para resfrestar el datatable luego del ingreso
        }catch (Exception e){
            throw e;
        }
        
        
    }
    
     private void modificar () throws Exception{ //Funcion para modificar, invoca los metodos para del patron de acceso DAO
        PersonaDAO dao;//Declara la Clase DAO
        
        try {
            dao = new PersonaDAO();
            dao.modificar(persona);
            this.listar(); //para resfrestar el datatable luego de la modificacion
        }catch (Exception e){
            throw e;
        }
        
        
         }
      public void eliminar (Persona per) throws Exception{ //Funcion para modificar, invoca los metodos para del patron de acceso DAO
        PersonaDAO dao;//Declara la Clase DAO
        
        try {
            dao = new PersonaDAO();
            dao.eliminar(per);
            this.listar(); //para resfrestar el datatable luego de la modificacion
        }catch (Exception e){
            throw e;
        }
        
        
         }
    
      
     
    //Llamando al metodo listar del boton para motrar en el datatable
    public void listar () throws Exception{ //Funcion para registrar, invoca los metodos para del patron de acceso DAO
        PersonaDAO dao;//Declara la Clase DAO
        
        try {
            dao = new PersonaDAO();
            lstPersonas = dao.listar();
        }catch (Exception e){
            throw e;
        }
        
        
         }
    
    //Leer id con parametro de entrada del datatable
    public void leerID(Persona per) throws Exception{
        PersonaDAO dao;
        Persona temp;
        try {
            dao = new PersonaDAO();
            temp=dao.leerID(per);
            //Variable temporal 
            if (temp != null){
            this.persona = temp; //asigno el valor global si es difernte de nulo y la variable tendra los valores correcto
            this.accion = "Modificar";
        }
        } catch (Exception e) {
            throw e;
        }
    }
    
    }
