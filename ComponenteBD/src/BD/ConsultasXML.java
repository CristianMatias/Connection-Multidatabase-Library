package BD;

import java.util.ArrayList;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author Cristian
 */
class ConsultasXML {
    private XQPreparedExpression consulta;
    private final ArrayList<Object> arrayResultados;
    private XQResultSequence resultados;
    private XQResultItem resultado;
    private XQConnection conexion;

    public ConsultasXML() {
        arrayResultados = new ArrayList<>();
    }
    
    protected boolean conectar(String BD, String usuario, String contraseña, String ip, String puerto){
        try{
            XQDataSource server = new ExistXQDataSource();
            server.setProperty("serverName", ip);
            server.setProperty("port", puerto);
            server.setProperty("user", usuario);
            server.setProperty("password", contraseña);
            conexion = server.getConnection();
            return true;
       }catch(XQException ex){
           return false;
       }
    }
    
    protected ArrayList<Object> consulta(String query) throws Exception{
        consulta = conexion.prepareExpression(query);
        resultados = consulta.executeQuery();
        if(resultados.next())  resultado = (XQResultItem) resultados.getItem();
        arrayResultados.add(resultado);
        return arrayResultados;
    }
}
