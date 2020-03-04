package BD;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * 
 * @author Cristian
 */
public class OracleTest {
    private static final ComponenteBD c = new ComponenteBD();
    
    @BeforeClass
    public static void connectTest() throws Exception{
        c.setModelo(ComponenteBD.ORACLE);
        assertTrue(c.connect("pruebas", "admin", "qwerty", "localhost", "1521"));
    }
    
    @Test
    public void selectTest() throws Exception{
        ArrayList<Object> lista = c.query("SELECT nombre FROM Persona WHERE id = 1");
        
        for (Object object : lista) {
            if(lista instanceof ResultSet){
                assertEquals("A", ((ResultSet) object).getString(0));
            }
        }
    }
    
    @Test
    public void updateTest() throws Exception{
        assertTrue(c.update("UPDATE Persona SET nombre = ? WHERE id = ?", 1, "X"));
    }
    
    @Test
    public void deleteTest() throws Exception{
        assertTrue(c.delete("DELETE FROM Persona WHERE id = ?", 3));
    }
    
    @Test
    public void insertTest() throws Exception{
        assertTrue(c.insert("insert into persona(id, nombre) values(5,'F');"));
    }
}
