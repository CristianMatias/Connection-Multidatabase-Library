package BD;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Cristian
 */
public class MySQLTest {
    private static final ComponenteBD c = new ComponenteBD();

    @Before
    public void connectTest(){
        c.setModelo(ComponenteBD.MYSQL);
        assertTrue(c.connect("prueba", "root", "qwerty", "localhost", "3306"));
    }
    
    @Test
    public void failConnect(){
        assertFalse(c.connect("prueba", "root", "admin", "localhost", "3306"));
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
    public void failSelect() throws Exception{
        ArrayList<Object> lista = c.query("SELECT nombre FROM Persona WHERE id = 80");
        
        for (Object object : lista) {
            if(lista instanceof ResultSet){
                assertEquals(null, ((ResultSet) object).getString(0));
            }
        }
    }    
    
    @Test
    public void updateTest() throws Exception{
        assertTrue(c.update("UPDATE Persona SET nombre = ? WHERE id = ?", 1, "X"));
    }
    
    @Test
    public void failUpdate() throws Exception{
        assertFalse(c.update("UPDATE Persona SET nombre = ? WHERE id = ?", 0, ""));
    }
    
    @Test
    public void deleteTest() throws Exception{
        assertTrue(c.delete("DELETE FROM Persona WHERE id = ?", 4));
    }
    
    @Test
    public void failDelete() throws Exception{
        assertFalse(c.delete("", 90));
    }
    
    @Test
    public void failInsert() throws Exception{
        assertFalse(c.insert("insert into persona(id, nombre) values(18,'F');"));
    }
    
    @Test
    public void insertTest() throws Exception{
        assertFalse(c.insert("insert into persona(id, nombre) values(21,'F');"));
    }
}
