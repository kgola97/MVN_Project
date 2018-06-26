package pl.pkr.kgola;

import junit.framework.Test;
import junit.framework.TestCase;

//import junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class BiblioItemTest
{


    //@Test
    public void shouldReturnCorrectFileName(){
        String fileName = "fileName";

        BiblioItem biblioItem = new TextDocument(fileName, null);

        assertEquals(fileName, biblioItem.getFileName());
    }




    //@Test
    public void shouldReturnCorrectGetType() {
        String type = "TextDocument";

        BiblioItem biblioItem = new TextDocument(null, type);

        assertEquals(type, biblioItem.getType());
    }
}
