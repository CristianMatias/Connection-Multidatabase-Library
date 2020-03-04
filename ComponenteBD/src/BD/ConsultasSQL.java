package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
class ConsultasSQL {
    private static PreparedStatement sentencia;
    private static ResultSet resultados;
    private static ArrayList<Object> listaResultados;
    private static Connection conexion;

    protected boolean connect(String BD, String usuario, String contraseña, String ip, String puerto,String tipo){
        try{
            conexion = DriverManager.
                        getConnection("jdbc:"+tipo+"://"+ip+":"+puerto+"/"+BD,usuario,contraseña);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    
    protected ArrayList<Object> query(String query) throws Exception {
        sentencia = conexion.prepareStatement(query);
        resultados = sentencia.executeQuery();
        listaResultados = new ArrayList<>();
        listaResultados.add(resultados);
        
        return listaResultados;
    }
    
    protected boolean update(String query, int primaryKey, String newParametrer) {
        try{
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(2, primaryKey);
            sentencia.setString(1, newParametrer);
            sentencia.executeUpdate();
            return true;
        }catch(SQLException ex){
            return false;
        }
    }

    protected boolean delete(String query, int primaryKey) {
        try{
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1, primaryKey);
            sentencia.execute();
            return true;
        }catch(SQLException ex){
            return false;
        }
    }

    protected boolean insert(String query) {
        try{
            sentencia = conexion.prepareStatement(query);
            sentencia.executeUpdate();
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
}
