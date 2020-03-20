package BD;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
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
            MongoClient mongoClient = new MongoClient( ip , Integer.parseInt(puerto) ); 
            db = mongoClient.getDB(BD);
            return true;
        }catch(Exception ex){
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
            return null;
        }
    }
    
    protected ArrayList readAll(String coleccion){
        try{
            listaResultados = new ArrayList<>();
            colection = db.getCollection(coleccion);
            DBCursor cursor = colection.find();
            while(cursor.hasNext()) listaResultados.add(cursor.next());
            return listaResultados;
        }catch(Exception ex){
            return null;
        }
    }
    
    protected boolean update(String campoID, String id, String campo, String update){
        try{
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.append("$set", new BasicDBObject().append(campo, update));

            BasicDBObject searchQuery = new BasicDBObject().append(campoID, id);
            colection.update(searchQuery, newDocument);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    protected boolean delete(String campo, String value){
        try {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put(campo, value);

            checkResult(colection.remove(searchQuery));

            return true;
        }catch(Exception ex) {
            return false;
        }
    }
    
    protected boolean insert(Object object){
        try{
            Gson gson = new Gson();
            BasicDBObject insert = (BasicDBObject) JSON.parse(gson.toJson(object));
            colection.insert(insert);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    private void checkResult(WriteResult result) throws Exception{
        if(result.getN() == 0) throw new Exception();
    }
}
