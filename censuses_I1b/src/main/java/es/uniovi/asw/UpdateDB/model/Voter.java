package es.uniovi.asw.UpdateDB.model;

/**
 * Created by Chamadoira on 17/02/2016.
 * @author UO236953
 */
public class Voter {
    private String name, dni, email, pass;
    private Integer stationCode;

    public Voter(String name, String dni, String email, int stationCode, String pass) {
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.stationCode = stationCode;
        this.pass = pass;
    }

    public Voter(String name, String dni, String email, int stationCode) {
    	this.name = name;
        this.dni = dni;
        this.email = email;
        this.stationCode = stationCode;
	}

	public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStationCode() {
        return stationCode;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString(){
        return "Name: "+getName()+", DNI: "+getDni()+", e-mail: "+getEmail()+", Poll Station Code: "+getStationCode();
    }

	public void setPass(String pass) {
		this.pass = pass;
	}
}
