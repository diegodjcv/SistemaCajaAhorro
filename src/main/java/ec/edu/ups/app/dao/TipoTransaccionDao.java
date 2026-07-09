package ec.edu.ups.app.dao;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.TipoTransaccion;

public class TipoTransaccionDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    // Insertar
    public void insertar(TipoTransaccion tipoTransaccion) {
        em.persist(tipoTransaccion);
    }

    // Actualizar
    public void actualizar(TipoTransaccion tipoTransaccion) {
        em.merge(tipoTransaccion);
    }

    // Eliminar
    public void eliminar(int id) {

        TipoTransaccion tipo = em.find(TipoTransaccion.class, id);

        if (tipo != null) {
            em.remove(tipo);
        }

    }

    // Buscar por ID
    public TipoTransaccion buscar(int id) {
        return em.find(TipoTransaccion.class, id);
    }

    // Listar todos
    public List<TipoTransaccion> listar() {

        String jpql = "SELECT t FROM TipoTransaccion t ORDER BY t.nombre";

        return em.createQuery(jpql, TipoTransaccion.class).getResultList();

    }

    // Buscar por nombre
    public TipoTransaccion buscarPorNombre(String nombre) {

        try {

            String jpql = "SELECT t FROM TipoTransaccion t WHERE t.nombre = :nombre";

            TypedQuery<TipoTransaccion> query =
                    em.createQuery(jpql, TipoTransaccion.class);

            query.setParameter("nombre", nombre);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

    // Buscar por descripción
    public List<TipoTransaccion> buscarPorDescripcion(String descripcion) {

        String jpql = "SELECT t FROM TipoTransaccion t WHERE t.descripcion LIKE :descripcion";

        TypedQuery<TipoTransaccion> query =
                em.createQuery(jpql, TipoTransaccion.class);

        query.setParameter("descripcion", "%" + descripcion + "%");

        return query.getResultList();

    }

    // Obtener el último tipo registrado
    public TipoTransaccion ultimoTipoTransaccion() {

        try {

            String jpql = "SELECT t FROM TipoTransaccion t ORDER BY t.id DESC";

            return em.createQuery(jpql, TipoTransaccion.class)
                     .setMaxResults(1)
                     .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

}