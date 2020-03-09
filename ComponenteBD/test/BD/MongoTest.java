package BD;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author Cristian
 */
public class MongoTest {
     private static final ComponenteBD c = new ComponenteBD();

    @Before
    public void connectTest(){
        c.setModelo(ComponenteBD.MONGO);
        assertTrue(c.connect("prueba", "", "", "localhost", "27017"));
    }
    
    @Test
    public void selectTest(){
        ArrayList<Object> resultados = c.query("nombre","RR HH", "departamentos");
        String expected = "{\"_id\": {\"$oid\": \"5e4ec58d4d4fed5086985571\"}, \"ubicacion\": \"SEVILLA\", \"numero\": 10, \"nombre\": \"RR HH\"}";
        resultados.forEach((resultado) -> {                   
            assertEquals(expected,resultado.toString());
         });
    }
}
