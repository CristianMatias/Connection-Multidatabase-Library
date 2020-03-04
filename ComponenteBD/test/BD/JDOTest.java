package BD;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Cristian
 */
public class JDOTest {
    private static final ComponenteBD c = new ComponenteBD();
    private Persona persona = new Persona(13,"E");
    
    @BeforeClass
    public static void connectTest(){
        c.setModelo(ComponenteBD.JPA);
        assertTrue(c.connect("persona.odb", "", "", "", ""));
    }
    
    @Test
    public void insertTest(){
        assertTrue(c.insert(persona));
    }

    @Test
    public void selectTest() throws Exception{
        ArrayList<Object> resultados = c.query("SELECT p FROM Persona p WHERE p.id = 13");
        for (Object resultado : resultados) {
            if(resultado instanceof Persona){
                persona = (Persona) resultado;
                assertEquals("Persona{" + "id=" + 13 + ", nombre=" + "E" + '}', resultado.toString());
            }
        }
    }
    
    @Test
    public void updateTest(){
        persona.setNombre("C");
        assertTrue(c.update(persona));
    }
    
    @Test
    public void deleteTest(){
        assertTrue(c.delete(persona));
    }   
}
