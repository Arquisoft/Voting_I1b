package es.uniovi.asw.DBUpdate;

import es.uniovi.asw.DBUpdate.InsertVoters;
import es.uniovi.asw.DBUpdate.Jdbc;
import es.uniovi.asw.DBUpdate.model.Voter;
import es.uniovi.asw.Parser.reader.Reader;
import es.uniovi.asw.Parser.reader.XSSFReader;

import org.junit.Test;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by uo237633 on 24/02/2016.
 * @author uo237633
 */
public class InsertDBTest {
    String file=".\\src\\test\\resources\\insertDBTest.xlsx";
    //String file="./src/test/java/es/uniovi/asw/jdbc/votersXSSFReaderTest.xlsx";
    
    @Test
    public void read() {
        Reader reader = new XSSFReader();
        try {
            List<Voter> voters= reader.processFile(file);

            InsertVoters.insert(voters);

//            String s = "Select * from voters where Name = 'Alberto'";
            
            String s = "SELECT v.nif, v.nombre, v.email, v.codColegioElectoral, v.password "
    			+ "FROM VOTANTES v WHERE v.nombre='Alberto'";

            try {
                Connection c = Jdbc.getConnection();

                PreparedStatement st = c.prepareStatement(s);

                ResultSet rs = st.executeQuery();

                Voter v = null;

                while(rs.next()){

                    v = new Voter(rs.getString(2), 
                    		rs.getString(1), 
                    		rs.getString(3), 
                    		rs.getInt(4), 
                    		rs.getString(5));
                }

                Voter v2 = voters.get(0);

                assertEquals(v2.getDni(), v.getDni());
                assertEquals(v2.getName(), v.getName());
                assertEquals(v2.getEmail(), v.getEmail());
                assertEquals(v2.getStationCode(), v.getStationCode());

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch(IOException e){
            System.out.println("The file '"+file+"' was not found");
        }
    }


}