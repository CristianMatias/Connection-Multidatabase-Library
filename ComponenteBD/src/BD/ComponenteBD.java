package BD;

import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public class ComponenteBD implements java.io.Serializable{
    public static final int MYSQL = 0;
    public static final int POSTGRE = 1;
    public static final int EXIST = 2;
    public static final int ORACLE = 3;
    public static final int JPA = 4;
    public static final int JDO = 5;
    private static ConsultasSQL sql;
    private static ConsultasXML xml;
    private static ConsultasJPA jpa;
    private static ConsultasJDO jdo;
    private String BD;
    private String usuario;
    private String contraseña;
    private String ip;
    private String puerto;
    private int modelo = 1;

    public ComponenteBD() {}

    public ComponenteBD(String BD, String usuario, String contraseña, String ip, String puerto){
        this.BD = BD;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.ip = ip;
        this.puerto = puerto;
        conectarBaseDatos();
    }
    
    public ComponenteBD(String BD){
        this.BD = BD;
        conectarBaseDatos();
    }
    
    public boolean connectBDOO(String BD){
        this.BD = BD;
        return conectarBaseDatos();
    }
    
    public boolean connect(String BD, String usuario, String contraseña, String ip, String puerto){
        this.BD = BD;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.ip = ip;
        this.puerto = puerto;
        return conectarBaseDatos();
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getModelo() {
        return modelo;
    }
    
    private boolean conectarBaseDatos(){
        try{
            switch(modelo){
                case MYSQL: 
                    sql = new ConsultasSQL();
                    return sql.connect(BD, usuario, contraseña, ip, puerto, "mysql");
                case POSTGRE: 
                    sql = new ConsultasSQL();
                    return sql.connect(BD, usuario, contraseña, ip, puerto, "postgresql");
                case ORACLE:
                     Class.forName("oracle.jdbc.driver.OracleDriver");  
                     sql = new ConsultasSQL();
                    return sql.connect(BD, usuario, contraseña, ip, puerto, "oracle:thin");
                case EXIST:
                    xml = new ConsultasXML();
                    return xml.conectar(BD, usuario, contraseña, ip, puerto);
                case JPA: 
                    jpa = new ConsultasJPA();
                    return jpa.connect(BD);
                case JDO:
                    jdo = new ConsultasJDO();
                    return jdo.connect(BD);
                default: return false;
            }
        }catch(ClassNotFoundException ex){
            return false;
        } 
    }
    
    
    public ArrayList<Object> query(String query) throws Exception{
        switch(modelo){
            case MYSQL:
            case POSTGRE:
            case ORACLE:
                return sql.query(query);
            case EXIST:
                return xml.consulta(query);
            case JPA:
                return jpa.query(query);
            case JDO:
                return jdo.query(query);
        }
        return null;
    }
    
    public boolean update(String query, int primaryKey, String newParametrer) throws Exception{
        return sql.update(query, primaryKey, newParametrer);
    }
    
    public boolean update(Object object){
        return insert(object);
    }
    
    public boolean insert(Object object){
         switch (modelo) {
            case JPA: return jpa.insert(object);
            case JDO: return jdo.insert(object);
            default: return false;
        }
    }
    
    public boolean delete(String query, int primaryKey) throws Exception{
        return sql.delete(query, primaryKey);
    }
    
    public boolean delete(Object object){
        switch (modelo) {
            case JPA: return jpa.delete(object);
            case JDO: return jdo.delete(object);
            default: return false;
        }
    }
    
    public boolean insert(String query) throws Exception{
        return sql.insert(query);
    }
    
}