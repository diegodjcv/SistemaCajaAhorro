package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.AsientoContable;

public class AsientoContableDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em; 

    // Insertar
    public void insertar(AsientoContable asiento) {
        em.persist(asiento);
    }

    // Actualizar
    public void actualizar(AsientoContable asiento) {
        em.merge(asiento);
    }

    // Eliminar
    public void eliminar(int id) {

        AsientoContable asiento = em.find(AsientoContable.class, id);

        if (asiento != null) {
            em.remove(asiento);
        }

    }

    // Buscar por ID
    public AsientoContable buscar(int id) {

        return em.find(AsientoContable.class, id);

    }

    // Listar todos
    public List<AsientoContable> listar() {

        String jpql = "SELECT a FROM AsientoContable a ORDER BY a.fecha ASC";

        return em.createQuery(jpql, AsientoContable.class).getResultList();

    }

    // Buscar por fecha
    public List<AsientoContable> buscarPorFecha(Date fecha) {

        String jpql = "SELECT a FROM AsientoContable a WHERE a.fecha = :fecha";

        TypedQuery<AsientoContable> query = em.createQuery(jpql, AsientoContable.class);

        query.setParameter("fecha", fecha);

        return query.getResultList();

    }

    // Buscar por descripción
    public List<AsientoContable> buscarDescripcion(String descripcion) {

        String jpql = "SELECT a FROM AsientoContable a WHERE a.descripcion LIKE :descripcion";

        TypedQuery<AsientoContable> query = em.createQuery(jpql, AsientoContable.class);

        query.setParameter("descripcion", "%" + descripcion + "%");

        return query.getResultList();

    }

    // Calcular total Debe
    public Double totalDebe() {

        String jpql = "SELECT SUM(a.debe) FROM AsientoContable a";

        Double total = em.createQuery(jpql, Double.class).getSingleResult();

        return total == null ? 0.0 : total;

    }

    // Calcular total Haber
    public Double totalHaber() {

        String jpql = "SELECT SUM(a.haber) FROM AsientoContable a";

        Double total = em.createQuery(jpql, Double.class).getSingleResult();

        return total == null ? 0.0 : total;

    }

    // Buscar último asiento
    public AsientoContable ultimoAsiento() {

        try {

            String jpql = "SELECT a FROM AsientoContable a ORDER BY a.id DESC";

            return em.createQuery(jpql, AsientoContable.class)
                     .setMaxResults(1)
                     .getSingleResult();

        } catch (NoResultException e) {

            return null; 

        }

    }
}

