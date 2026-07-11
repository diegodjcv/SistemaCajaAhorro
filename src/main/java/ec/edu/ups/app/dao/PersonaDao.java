package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.Persona;

public class PersonaDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public void insertar(Persona persona) {
        em.persist(persona);
    }

    public void actualizar(Persona persona) {
        em.merge(persona);
    }

    public void eliminar(int id) {
        Persona p = em.find(Persona.class, id);
        if (p != null) {
            em.remove(p); 
        }
        }
    }
    public void eliminar(String cedula) {
        Persona p = em.find(Persona.class, cedula);
        try {
            String jpql = "DELETE p FROM Persona p WHERE p.cedula=:cedula";
            TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
            query.setParameter("cedula", cedula);
        }catch(Exception e) {
        	
        }
    }

    public Persona buscar(String cedula) {
        return em.find(Persona.class, cedula); 
    }

    public List<Persona> listar() {
        String jpql = "SELECT p FROM Persona p";
        return em.createQuery(jpql, Persona.class).getResultList();
    }

    public Persona buscarPorCedula(String cedula) {
        try {
            String jpql = "SELECT p FROM Persona p WHERE p.cedula=:cedula";
            TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
            query.setParameter("cedula", cedula);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Persona iniciarSesion(String usuario, String password) {
        try {
            String jpql = "SELECT p FROM Persona p WHERE p.username=:u AND p.contrasena=:c";
            TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
            query.setParameter("u", usuario);
            query.setParameter("c", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    

    public void cerrarSesion() {
    }
}
