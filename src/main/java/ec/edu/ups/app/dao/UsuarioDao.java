package ec.edu.ups.app.dao;

import ec.edu.ups.app.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    public void insert(Usuario usuario) {
        em.persist(usuario);
    }

    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    public void delete(int codigo) {

        Usuario u = read(codigo);

        if (u != null)
            em.remove(u);

    }

    public Usuario read(int codigo) {
        return em.find(Usuario.class, codigo);
    }

    public List<Usuario> getAll() {

        return em.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();

    }

    public Usuario buscarUsername(String username) {

        try {

            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username=:u",
                    Usuario.class)
                    .setParameter("u", username)
                    .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

    public Usuario login(String username, String password) {

        try {

            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username=:user AND u.password=:pass AND u.estado=true",
                    Usuario.class)
                    .setParameter("user", username)
                    .setParameter("pass", password)
                    .getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

}