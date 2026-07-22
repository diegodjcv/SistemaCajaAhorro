package ec.edu.ups.app.test;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import ec.edu.ups.app.dao.CreditoDao;
import ec.edu.ups.app.modelo.Credito;

public class CreditoTest {
	private CreditoDao creditoDao;

    private EntityManager em;


    @BeforeEach
    public void preparar() {

        creditoDao = new CreditoDao();

        em = Mockito.mock(EntityManager.class);

        // Inyección manual del EntityManager
        creditoDao.em= em;

    }


    @Test
    public void insertarCreditoTest() {


        Credito credito = new Credito();


        creditoDao.insertar(credito);


        Mockito.verify(em)
                .persist(credito);

    }



    @Test
    public void actualizarCreditoTest() {


        Credito credito = new Credito();


        creditoDao.actualizar(credito);


        Mockito.verify(em)
                .merge(credito);

    }


    @Test
    public void buscarCreditoTest() {


        Credito credito = new Credito();


        Mockito.when(
                em.find(Credito.class,1)
        )
        .thenReturn(credito);



        Credito resultado =
                creditoDao.buscar(1);



        assertNotNull(resultado);

        assertEquals(
                credito,
                resultado
        );

    }




    @Test
    public void eliminarCreditoTest() {


        Credito credito =
                new Credito();


        Mockito.when(
                em.find(Credito.class,1)
        )
        .thenReturn(credito);



        creditoDao.eliminar(1);



        Mockito.verify(em)
                .remove(credito);

    }



    @Test
    public void solicitarCreditoTest(){


        Credito credito =
                new Credito();



        creditoDao.solicitarCredito(credito);

        assertEquals(
                "Pendiente",
                
                credito.getEstado()
        );


        Mockito.verify(em)
                .persist(credito);

    }


    @Test
    public void aprobarCreditoTest(){


        Credito credito =
                new Credito();


        Mockito.when(
                em.find(Credito.class,1)
        )
        .thenReturn(credito);



        creditoDao.aprobarCredito(1);



        assertEquals(
                "Aprobado",
                credito.getEstado()
        );


        Mockito.verify(em)
                .merge(credito);

    }


    @Test
    public void rechazarCreditoTest(){


        Credito credito =
                new Credito();


        Mockito.when(
                em.find(Credito.class,1)
        )
        .thenReturn(credito);



        creditoDao.rechazarCredito(1);



        assertEquals(
                "Rechazado",
                credito.getEstado()
        );


        Mockito.verify(em)
                .merge(credito);

    }


}
