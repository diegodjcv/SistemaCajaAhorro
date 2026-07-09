package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.app.modelo.Rol;

public class RolDao implements Serializable{

    @Inject
    private EntityManager em;

    public void insertar(Rol rol){
        em.persist(rol);
    }

    public void actualizar(Rol rol){
        em.merge(rol);
    }

    public void eliminar(int id){
        Rol r = em.find(Rol.class,id);
        if(r!=null)
            em.remove(r);
    }

    public Rol buscar(int id){
        return em.find(Rol.class,id);
    }

    public List<Rol> listar(){
        return em.createQuery("SELECT r FROM Rol r",Rol.class).getResultList();
    }
}

