package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.Persona;

@ApplicationScoped
public class PersonaDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public void guardar(Persona persona) {
        em.persist(persona);
    }

    public void actualizar(Persona persona) {
        em.merge(persona);
    }

        public List<Persona> listar() {

        TypedQuery<Persona> query = em.createQuery(
                "SELECT p FROM Persona p",
                Persona.class);

        return query.getResultList();
    }

    public Persona buscarPorCedula(String cedula) {

        try {

            TypedQuery<Persona> query = em.createQuery(
                    "SELECT p FROM Persona p WHERE p.cedula = :cedula",
                    Persona.class);

            query.setParameter("cedula", cedula);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    public Persona iniciarSesion(String username, String password) {

        try {

            TypedQuery<Persona> query = em.createQuery(
                    "SELECT p FROM Persona p "
                    + "WHERE p.username = :username "
                    + "AND p.password = :password",
                    Persona.class);

            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    // Cerrar sesión
    public void cerrarSesion() {
        System.out.println("Sesión cerrada ");
    }


	
}
