package BD;

import java.util.ArrayList;
import javax.xml.xquery.XQResultItem;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Cristian
 */
public class XMLTest {
    private static final ComponenteBD c = new ComponenteBD();
    
    @BeforeClass
    public static void connectTest() throws Exception{
        c.setModelo(ComponenteBD.EXIST);
        assertTrue(c.connect("departamentos", "admin", "", "localhost", "8080"));
    }
    
    @Test
    public void select() throws Exception{
        ArrayList<Object> lista = c.query("//departamentos/departamento[nombre = 'VENTAS']/ubicacion");
        for (Object object : lista) {
            if(object instanceof XQResultItem){
                assertEquals("<ubicacion>BARCELONA</ubicacion>", ((XQResultItem) object).getItemAsString(null));
            }
        }
    }
}
