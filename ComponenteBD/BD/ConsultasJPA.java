package BD;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Cristian
 */
class ConsultasJPA {
    private Query consulta;
    private final ArrayList<Object> listaResultados;
    private static EntityManager conexion;

    public ConsultasJPA() {
        this.listaResultados = new ArrayList<>();
    }
    
    protected boolean connect(String db){
        try{
            EntityManagerFactory manager = Persistence.createEntityManagerFactory(db);
            conexion = manager.createEntityManager();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    private void startTransaction(){
        conexion.getTransaction().begin();
    }
    
    private boolean endTransaction(){
        try{
            conexion.getTransaction().commit();
            return true;
        }catch(Exception ex){
            conexion.getTransaction().rollback();
            return false;
        }
    }
    
    protected ArrayList<Object> query(String query){
        consulta = conexion.createQuery(query);
        listaResultados.addAll(consulta.getResultList());
        return listaResultados;
    }
    
    protected boolean insert(Object object){
        startTransaction();
        conexion.persist(object);
        return endTransaction();
    }
    
    protected boolean delete(Object object){
        startTransaction();
        conexion.remove(object);
        return endTransaction();
    }
}
