package es.uniovi.asw.ReportWriter;

import org.junit.Test;

import es.uniovi.asw.Parser.RandomPassGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import static org.junit.Assert.assertEquals;
/**
 * Created by Chamadoira on 24/02/2016.
 * @author UO236953
 */
public class TxtWriterTest {
    String file=".\\src\\test\\resources\\TxtWriterTest.txt";

    @Test
    public void textGeneratorTest(){
    	TxtWriter rw = new TxtWriter();
        rw.add("Message 1");
        rw.add("Message 2");
        rw.add("Message 3");
        assertEquals(rw.toString(),"Message 1\nMessage 2\nMessage 3\n");
        rw.clean();
        assertEquals(rw.toString(),"");
    }
    @Test
    public void txtWriter(){
    	TxtWriter rw = new TxtWriter();
        rw.add("Message 1");
        rw.add("Message 2");
        rw.add("Message 3");

        String randomNameFile =RandomPassGenerator.getRandomPass();
        rw.toFile(randomNameFile);
        try {
            BufferedReader fileCreated = new BufferedReader(new FileReader(randomNameFile+".txt"));
            BufferedReader fileOriginal = new BufferedReader(new FileReader(file));
            assertEquals( fileCreated.readLine(),fileOriginal.readLine());

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


    }
}
