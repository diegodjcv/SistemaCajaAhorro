package ec.edu.ups.app.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ec.edu.ups.app.modelo.Persona;



public class PersonaTest {


    @Test
    public void crearPersonaTest() {


        Persona persona = new Persona();

        persona.setCedula("0106625221");

        persona.setNombres("diego javier");
        persona.setApellidos("cabrera vanegas");
        persona.setCorreo("diegodjvc@gmail.com");
        persona.setUsername("djcv1994");
        persona.setPassword("123456");


        assertEquals(
                "0106625221",
                persona.getCedula()
        );


        assertEquals(
                "diego javier",
                persona.getNombres()
        );


        assertEquals(
                "cabrera vanegas",
                persona.getApellidos()
        );


        assertEquals(
                "diegodjvc@gmail.com",
                persona.getCorreo()
        );
        assertEquals(
                "diegodjv1994",
                persona.getCorreo()
        );

        assertEquals(
                "123456",
                persona.getPassword()
        );

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
   