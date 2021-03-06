package es.uniovi.asw.UpdateDB;

import es.uniovi.asw.UpdateDB.Jdbc;
import es.uniovi.asw.UpdateDB.model.Voter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by uo237633 on 21/02/2016.
 */
public final class InsertVoters {
	
    private InsertVoters(){
    }

    public static void insert(List<Voter> voters) {
//        String s = "Insert into voters values(?,?,?,?,?)";
    	String s = "INSERT INTO VOTANTES "
    			+ "(nif, nombre, email, codColegioElectoral, password, haVotado, votoElectronico) "
    			+ "VALUES (?, ?, ?, ?, ?, 0, 0)";
//        String q = "Select * from voters where NIF = ?";
    	String q = "SELECT v.nif, v.nombre, v.email, v.codColegioElectoral, v.password "
    			+ "FROM VOTANTES v WHERE v.nif=?";

        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        int row = 0;

        try {
            c = Jdbc.getConnection();
            ps = c.prepareStatement(s);
            
            for (Voter v : voters) {
                row++;
                ps2 = c.prepareStatement(q);

                ps2.setString(1, v.getDni());

                rs = ps2.executeQuery();

                if (!rs.isBeforeFirst()) {
                    ps.setString(1, v.getDni());
                    ps.setString(2, v.getName());
                    ps.setString(3, v.getEmail());
                    ps.setInt(4, v.getStationCode());
                    ps.setString(5, v.getPass());

                    ps.executeUpdate();
                } else {
                    System.out.println("The voter " + v.getDni() + " has already been added");

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null)
                Jdbc.close(c);
            if(ps != null)
                Jdbc.close(ps);
        }
    }

	public static void checkMissingFields(List<Voter> voters) {
		for(Voter voter : voters){
			if(voter.getDni() == null || voter.getDni().isEmpty()){
				throw new IllegalStateException("The NIF is missing");
			}
			if(voter.getName() == null || voter.getName().isEmpty()){
				throw new IllegalStateException(
						"The name of the voter of NIF " + voter.getDni() + " is missing");
			}
			if(voter.getEmail() == null || voter.getEmail().isEmpty()){
				throw new IllegalStateException(
						"The email of the voter of NIF " + voter.getDni() + " is missing");
			}
			if(voter.getPass() == null || voter.getPass().isEmpty()){
				throw new IllegalStateException(
						"The password of the voter of NIF " + voter.getDni() + " is missing");
			}
			if(voter.getStationCode() == null){
				throw new IllegalStateException(
						"The polling station code of the voter of NIF " + voter.getDni() + " is missing");
			}
		}
	}
}
