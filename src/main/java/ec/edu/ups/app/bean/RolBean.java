package ec.edu.ups.app.bean;

import ec.edu.ups.app.bussiness.RolON;
import ec.edu.ups.app.modelo.Rol;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class RolBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private RolON rolON;

    private Rol rol;

    private List<Rol> listaRoles;

    @PostConstruct
    public void init() {
        nuevo();
        listar();
    }

    /**
     * Guarda o actualiza un rol.
     */
    public void guardar() {

        try {

            rolON.guardar(rol);

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Correcto",
                            "Rol guardado correctamente."));

            nuevo();
            listar();

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()));

        }

    }

    /**
     * Selecciona un rol para editar.
     */
    public void editar(Rol rol) {

        this.rol = rol;

    }

    /**
     * Elimina un rol.
     */
    public void eliminar(Rol rol) {

        try {

            rolON.eliminar(rol.getId());

            listar();

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Correcto",
                            "Rol eliminado correctamente."));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()));

        }

    }

    /**
     * Busca un rol por ID.
     */
    public void buscar() {

        if (rol.getId() != 0) {

            rol = rolON.buscar(rol.getId());

            if (rol == null) {

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN,
                                "Aviso",
                                "No se encontró el rol."));

                nuevo();

            }

        }

    }

    /**
     * Lista todos los roles.
     */
    public void listar() {

        listaRoles = rolON.listar();

    }

    /**
     * Limpia el formulario.
     */
    public void nuevo() {

        rol = new Rol();

    }

    //=========================
    // Getters y Setters
    //=========================

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

}