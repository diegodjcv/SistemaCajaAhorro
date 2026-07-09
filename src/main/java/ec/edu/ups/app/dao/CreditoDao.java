package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.Credito;
import ec.edu.ups.app.modelo.Socio;

public class CreditoDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    // Insertar crédito
    public void insertar(Credito credito) {
        em.persist(credito);
    }

    // Actualizar crédito
    public void actualizar(Credito credito) {
        em.merge(credito);
    }

    // Eliminar crédito
    public void eliminar(int id) {
        Credito credito = em.find(Credito.class, id);
        if (credito != null) {
            em.remove(credito);
        }
    }

    // Buscar por ID
    public Credito buscar(int id) {
        return em.find(Credito.class, id);
    }

    // Listar todos los créditos
    public List<Credito> listar() {
        String jpql = "SELECT c FROM Credito c";
        return em.createQuery(jpql, Credito.class).getResultList();
    }

    // Solicitar crédito
    public void solicitarCredito(Credito credito) {
        credito.setEstado("Pendiente");
        em.persist(credito);
    }

    // Aprobar crédito
    public void aprobarCredito(int id) {
        Credito credito = buscar(id);

        if (credito != null) {
            credito.setEstado("Aprobado");
            em.merge(credito);
        }
    }

    // Rechazar crédito
    public void rechazarCredito(int id) {
        Credito credito = buscar(id);

        if (credito != null) {
            credito.setEstado("Rechazado");
            em.merge(credito);
        }
    }

    // Buscar créditos por estado
    public List<Credito> buscarEstado(String estado) {

        String jpql = "SELECT c FROM Credito c WHERE c.estado = :estado";

        TypedQuery<Credito> query = em.createQuery(jpql, Credito.class);
        query.setParameter("estado", estado);

        return query.getResultList();
    }

    // Buscar créditos pendientes
    public List<Credito> listarPendientes() {

        return buscarEstado("Pendiente");

    }

    // Buscar créditos por socio
    public List<Credito> buscarPorSocio(Socio socio) {

        String jpql = "SELECT c FROM Credito c WHERE c.socio = :socio";

        TypedQuery<Credito> query = em.createQuery(jpql, Credito.class);
        query.setParameter("socio", socio);

        return query.getResultList();

    }

    // Buscar el último crédito registrado
    public Credito ultimoCredito() {

        try {

            String jpql = "SELECT c FROM Credito c ORDER BY c.id DESC";

            return em.createQuery(jpql, Credito.class)
                     .setMaxResults(1)
                     .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }

}
