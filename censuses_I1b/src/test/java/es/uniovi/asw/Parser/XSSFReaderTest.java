package es.uniovi.asw.Parser;

import static org.junit.Assert.assertEquals;

import es.uniovi.asw.Parser.parser.Parser;
import es.uniovi.asw.Parser.parser.XSSFParser;
import es.uniovi.asw.UpdateDB.model.Voter;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Chamadoira on 24/02/2016.
 * @author UO236953
 */
public class XSSFReaderTest {
    String file=".\\src\\test\\resources\\XSSFReaderTest.xlsx";

    @Test
    public void read() {
    	Parser reader = new XSSFParser();
        try {
            List<Voter> voters= reader.processFile(file);

            Voter a = voters.get(0);
            assertEquals(a.toString(),"Name: Alberto, DNI: 1111H, e-mail: alberto@gmail.com, Poll Station Code: 1");
//            System.out.println("Alberto's pass: "+a.getPass());

            Voter b =voters.get(1);
            assertEquals(b.toString(),"Name: Fernando, DNI: 22222J, e-mail: fernando@outlook.com, Poll Station Code: 2");
//            System.out.println("Fernando's pass: "+b.getPass());
        }catch(IOException e){
            System.out.println("The file '"+file+"' was not found");
        }
    }


}