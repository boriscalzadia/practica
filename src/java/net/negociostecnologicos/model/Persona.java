
package net.negociostecnologicos.model;

/**
 *
 * @author cbmartinez
 */
public class Persona {

 

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
   
    private int codigo;
    private String sexo;
    private String nombre;
    
    
}
