package ec.edu.ups.app.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import ec.edu.ups.app.bussiness.PersonaON;
import ec.edu.ups.app.modelo.Persona;

class PersonaTest {


	    @Test
	    public void crearPersonaTest() {


	        Persona persona = new Persona();

	        persona.setCedula("0106524275");

	        persona.setNombres("carmen alexandra");
	        persona.setApellidos("guaman yanza");
	        persona.setCorreo("alexandraguaman@gmail.com");
	        persona.setUsername("alexg");
	        persona.setPassword("123");


	        PersonaON personaOn=new PersonaON();
	        personaOn.guardar(persona);
	       
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
	                persona.getUsername()
	        );

	        assertEquals(
	                "123",
	                persona.getPassword()
	        );
	        
	        
	    }

	    @Testable
	    public void buscarPersona() {
	    	PersonaON personaOn=new PersonaON();
	    	 Persona p =new Persona();
		        p=personaOn.buscar("0106625221");
		        
		        System.out.println("<<<<<<<<<<<<"+p.getNombres()+" "+p.getApellidos()+">>>>>>>>>>>>");
		        
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
