package ec.edu.ups.app.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ec.edu.ups.app.dao.PersonaDao;
import ec.edu.ups.app.modelo.Persona;

class PersonaTest {


	    @Test
	    public void crearPersonaTest() {


	        Persona persona = new Persona();

	        persona.setCedula("0106524275");

	        persona.setNombres("carmen guaman");
	        persona.setApellidos("guaman yanza");
	        persona.setCorreo("alexandraguaman@gmail.com");
	        persona.setUsername("alexg");
	        persona.setPassword("123");


	        assertEquals(
	                "0106524275",
	                persona.getCedula()
	        );


	        assertEquals(
	                "carmen alexandra",
	                persona.getNombres()
	        );


	        assertEquals(
	                "guaman yanza",
	                persona.getApellidos()
	        );


	        assertEquals(
	                "alexandraguaman@gmail.com",
	                persona.getCorreo()
	        );
	        assertEquals(
	                "alexg",
	                persona.getCorreo()
	        );

	        assertEquals(
	                "123",
	                persona.getPassword()
	        );
	        PersonaDao personaDao=new PersonaDao();
	        personaDao.insertar(persona);
	    }



	    @Test
	    public void personaCedulaNoDebeSerNula() {


	        Persona persona = new Persona();


	        persona.setCedula("0106625221");


	        assertNotNull(
	                persona.getCedula()
	        );

	    }
}
