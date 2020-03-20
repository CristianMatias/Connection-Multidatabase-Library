package BD;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 *
 * @author Cristian
 */
class ConsultasJDO {
    private static PersistenceManager conexion = null;
    private final ArrayList<Object> listaResultados;
    private Query consulta;

    public ConsultasJDO() {
        this.listaResultados = new ArrayList<>();
    }
    
    
    
    protected boolean connect(String db){
        try{
            Properties propiedades = new Properties();
            propiedades.setProperty("javax.jdo.PersistenceManagerFactoryClass",
                "com.objectdb.jdo.PMF");
            propiedades.setProperty("javax.jdo.option.ConnectionURL",db);

            PersistenceManagerFactory manager = JDOHelper.getPersistenceManagerFactory(propiedades);
            conexion = manager.getPersistenceManager();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    protected ArrayList<Object> query(String query){
        consulta = conexion.newQuery(query);
        listaResultados.addAll((Collection<? extends Object>) consulta.execute());
        return listaResultados;
    }
    
    protected boolean insert(Object object){
        startTransaction();
        conexion.makePersistent(object);
        return endTransaction();
    }
    
    protected boolean delete(Object object){
        startTransaction();
        conexion.deletePersistent(object);
        return endTransaction();
    }
    
    private void startTransaction(){
        conexion.currentTransaction().begin();
    }
    
    private boolean endTransaction(){
        try{
            conexion.currentTransaction().commit();
            return true;
        }catch(Exception ex){
            conexion.currentTransaction().rollback();
            return false;
        }
    }
}
