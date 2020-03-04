package BD;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Cristian
 */
@Entity
public class Persona implements java.io.Serializable{
    
    @Id
    private int id;
    
    @Basic
    private String nombre;

    public Persona() {
    }

    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
}
