package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.TransaccionDiario;

public class TransaccionDiarioDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    // Insertar
    public void insertar(TransaccionDiario transaccionDiario) {
        em.persist(transaccionDiario);
    }

    // Actualizar
    public void actualizar(TransaccionDiario transaccionDiario) {
        em.merge(transaccionDiario);
    }

    // Eliminar
    public void eliminar(int id) {

        TransaccionDiario td = em.find(TransaccionDiario.class, id);

        if (td != null) {
            em.remove(td);
        }

    }

    // Buscar por ID
    public TransaccionDiario buscar(int id) {
        return em.find(TransaccionDiario.class, id);
    }

    // Listar todos
    public List<TransaccionDiario> listar() {

        String jpql = "SELECT t FROM TransaccionDiario t ORDER BY t.fecha DESC";

        return em.createQuery(jpql, TransaccionDiario.class).getResultList();

    }

    // Buscar por fecha
    public List<TransaccionDiario> buscarPorFecha(Date fecha) {

        String jpql = "SELECT t FROM TransaccionDiario t WHERE t.fecha = :fecha";

        TypedQuery<TransaccionDiario> query =
                em.createQuery(jpql, TransaccionDiario.class);

        query.setParameter("fecha", fecha, TemporalType.DATE);

        return query.getResultList();

    }

    // Buscar por descripción
    public List<TransaccionDiario> buscarDescripcion(String descripcion) {

        String jpql = "SELECT t FROM TransaccionDiario t WHERE t.descripcion LIKE :descripcion";

        TypedQuery<TransaccionDiario> query =
                em.createQuery(jpql, TransaccionDiario.class);

        query.setParameter("descripcion", "%" + descripcion + "%");

        return query.getResultList();

    }

    // Listar por tipo de movimiento
    public List<TransaccionDiario> buscarTipoMovimiento(String tipoMovimiento) {

        String jpql = "SELECT t FROM TransaccionDiario t WHERE t.tipoMovimiento = :tipo";

        TypedQuery<TransaccionDiario> query =
                em.createQuery(jpql, TransaccionDiario.class);

        query.setParameter("tipo", tipoMovimiento);

        return query.getResultList();

    }

    // Obtener la última transacción registrada
    public TransaccionDiario ultimaTransaccion() {

        try {

            String jpql = "SELECT t FROM TransaccionDiario t ORDER BY t.id DESC";

            return em.createQuery(jpql, TransaccionDiario.class)
                     .setMaxResults(1)
                     .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

}
