package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.Credito;
import ec.edu.ups.app.modelo.Cuota;

public class CuotaDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    // Insertar
    public void insertar(Cuota cuota) {
        em.persist(cuota);
    }

    // Actualizar
    public void actualizar(Cuota cuota) {
        em.merge(cuota);
    }

    // Eliminar
    public void eliminar(int id) {

        Cuota cuota = em.find(Cuota.class, id);

        if (cuota != null) {
            em.remove(cuota);
        }
    }

    // Buscar por ID
    public Cuota buscar(int id) {

        return em.find(Cuota.class, id);

    }

    // Listar todas las cuotas
    public List<Cuota> listar() {

        String jpql = "SELECT c FROM Cuota c ORDER BY c.numeroCuota";

        return em.createQuery(jpql, Cuota.class).getResultList();

    }

    // Buscar cuotas por crédito
    public List<Cuota> buscarPorCredito(Credito credito) {

        String jpql = "SELECT c FROM Cuota c WHERE c.credito = :credito ORDER BY c.numeroCuota";

        TypedQuery<Cuota> query = em.createQuery(jpql, Cuota.class);

        query.setParameter("credito", credito);

        return query.getResultList();

    }

    // Buscar cuotas pendientes
    public List<Cuota> listarPendientes() {

        String jpql = "SELECT c FROM Cuota c WHERE c.estado = 'PENDIENTE'";

        return em.createQuery(jpql, Cuota.class).getResultList();

    }

    // Buscar cuotas pagadas
    public List<Cuota> listarPagadas() {

        String jpql = "SELECT c FROM Cuota c WHERE c.estado = 'PAGADA'";

        return em.createQuery(jpql, Cuota.class).getResultList();

    }

    // Registrar pago de cuota
    public void registrarPago(int id) {

        Cuota cuota = buscar(id);

        if (cuota != null) {

            cuota.setEstado("PAGADA");

            em.merge(cuota);

        }

    }

    // Buscar por número de cuota
    public Cuota buscarNumeroCuota(int numeroCuota) {

        try {

            String jpql = "SELECT c FROM Cuota c WHERE c.numeroCuota = :numero";

            TypedQuery<Cuota> query = em.createQuery(jpql, Cuota.class);

            query.setParameter("numero", numeroCuota);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

    // Obtener la última cuota registrada
    public Cuota ultimaCuota() {

        try {

            String jpql = "SELECT c FROM Cuota c ORDER BY c.id DESC";

            return em.createQuery(jpql, Cuota.class)
                     .setMaxResults(1)
                     .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

}