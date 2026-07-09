package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javax.persistence.NoResultException;

import ec.edu.ups.app.modelo.Persona;
import ec.edu.ups.app.modelo.Socio;

public class SocioDao implements Serializable {

	    private static final long serialVersionUID = 1L;
	    @Inject
	    private EntityManager em;

	    public void guardar(Socio socio) {
	        em.persist(socio);
	    }

	    public void actualizar(Socio socio) {
	        em.merge(socio);
	    }
	    public void eliminar(String cedula) {
	        Socio s = em.find(Socio.class, cedula);
	        try {
	            String jpql = "DELETE s FROM Socio s WHERE s.cedula=:cedula";
	            TypedQuery<Socio> query = em.createQuery(jpql, Socio.class);
	            query.setParameter("cedula", cedula);
	        }catch(Exception e) {
	        	
	        }
	    }

	    public Socio buscarPorId(int id) {
	        return em.find(Socio.class, id);
	    }

	    public List<Socio> listar() {
	        TypedQuery<Socio> query = em.createQuery(
	                "SELECT s FROM Socio s", Socio.class);
	        return query.getResultList();
	    }
    
	    public Socio buscarPorCedula(String cedula) {
	        try {
	            return em.createQuery(
	                    "SELECT s FROM Socio s WHERE s.cedula = :cedula",
	                    Socio.class)
	                    .setParameter("cedula", cedula)
	                    .getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

	
}
