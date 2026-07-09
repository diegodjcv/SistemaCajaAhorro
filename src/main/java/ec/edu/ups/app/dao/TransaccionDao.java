package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.Cuenta;
import ec.edu.ups.app.modelo.Transaccion;
import ec.edu.ups.app.modelo.TipoTransaccion;

public class TransaccionDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    // Insertar
    public void insertar(Transaccion transaccion) {
        em.persist(transaccion);
    }

    // Actualizar
    public void actualizar(Transaccion transaccion) {
        em.merge(transaccion);
    }

    // Eliminar
    public void eliminar(int id) {

        Transaccion transaccion = em.find(Transaccion.class, id);

        if (transaccion != null) {
            em.remove(transaccion);
        }

    }

    // Buscar por ID
    public Transaccion buscar(int id) {

        return em.find(Transaccion.class, id);

    }

    // Listar todas las transacciones
    public List<Transaccion> listar() {

        String jpql = "SELECT t FROM Transaccion t ORDER BY t.fecha DESC";

        return em.createQuery(jpql, Transaccion.class).getResultList();

    }

    // Buscar por cuenta
    public List<Transaccion> buscarPorCuenta(Cuenta cuenta) {

        String jpql = "SELECT t FROM Transaccion t WHERE t.cuenta = :cuenta";

        TypedQuery<Transaccion> query = em.createQuery(jpql, Transaccion.class);

        query.setParameter("cuenta", cuenta);

        return query.getResultList();

    }

    // Buscar por tipo de transacción
    public List<Transaccion> buscarPorTipo(TipoTransaccion tipo) {

        String jpql = "SELECT t FROM Transaccion t WHERE t.tipoTransaccion = :tipo";

        TypedQuery<Transaccion> query = em.createQuery(jpql, Transaccion.class);

        query.setParameter("tipo", tipo);

        return query.getResultList();

    }

    // Buscar por fecha
    public List<Transaccion> buscarPorFecha(Date fecha) {

        String jpql = "SELECT t FROM Transaccion t WHERE t.fecha = :fecha";

        TypedQuery<Transaccion> query = em.createQuery(jpql, Transaccion.class);

        query.setParameter("fecha", fecha);

        return query.getResultList();

    }

    // Buscar por rango de montos
    public List<Transaccion> buscarPorMonto(double minimo, double maximo) {

        String jpql = "SELECT t FROM Transaccion t WHERE t.monto BETWEEN :minimo AND :maximo";

        TypedQuery<Transaccion> query = em.createQuery(jpql, Transaccion.class);

        query.setParameter("minimo", minimo);
        query.setParameter("maximo", maximo);

        return query.getResultList();

    }

    // Buscar última transacción
    public Transaccion ultimaTransaccion() {

        try {

            String jpql = "SELECT t FROM Transaccion t ORDER BY t.id DESC";

            return em.createQuery(jpql, Transaccion.class)
                     .setMaxResults(1)
                     .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

}