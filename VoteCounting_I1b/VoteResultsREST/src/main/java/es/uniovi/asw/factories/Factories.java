package es.uniovi.asw.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Factories {

	@Autowired
	PersistenceFactory pers;
	@Autowired
	BusinessFactory bus;
	
	public BusinessFactory getBusinessFactory() {
		return bus;
	}

	public PersistenceFactory getPersistenceFactory() {
		return pers;		
	}

}
