package BD;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoIterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Cristian
 */
class ConsultasMongoDB {
    private static DBCollection colection;
    private static ArrayList<Object> listaResultados;
    private static DB db;
    
    protected boolean connect(String BD, String ip, String puerto){
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 ); 
            db = mongoClient.getDB("prueba");
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    protected ArrayList<Object> query(String campo, String query,String coleccion){
        try{
            listaResultados = new ArrayList<>();
            colection = db.getCollection(coleccion);
            BasicDBObject busqueda = new BasicDBObject();
            busqueda.put(campo, query);
            DBCursor resultados = colection.find(busqueda);
            while(resultados.hasNext()){
                listaResultados.add(resultados.next());
            }
            return listaResultados;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    protected ArrayList<Object> queryListConnections(){
        try{
            listaResultados = new ArrayList<>();
            Set<String> colections = db.getCollectionNames();
            Iterator<String> iteratos = colections.iterator();
            
            while(iteratos.hasNext()) listaResultados.add(iteratos.next());
            return listaResultados;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    protected boolean update(DBObject original, DBObject update){
        try{
            colection.update(original, update);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
