package ec.edu.ups.app.bean;

import ec.edu.ups.app.bussiness.PersonaON;
import ec.edu.ups.app.modelo.Persona;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("personaBean")
@ViewScoped
public class PersonaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PersonaON personaON;

    private Persona persona;
    private List<Persona> personas;

    @PostConstruct
    public void init() {
        nuevo();
        listar();
    }

    /**
     * Guarda o actualiza una persona.
     */
    public void guardar() {
        try {
            personaON.guardar(persona);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Éxito",
                            "Persona guardada correctamente."));

            nuevo();
            listar();

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()));

            e.printStackTrace();
        }
    }

    /**
     * Carga una persona para editar.
     */
    public void editar(Persona p) {
        this.persona = p;
    }

    /**
     * Elimina una persona.
     */
    public void eliminar(Persona p) {

        try {

            personaON.eliminar(p.getCedula());

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Éxito",
                            "Persona eliminada correctamente."));

            listar();

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()));

            e.printStackTrace();

        }

    }

    /**
     * Inicializa una nueva persona.
     */
    public void nuevo() {
        persona = new Persona();
    }

    /**
     * Consulta todas las personas.
     */
    public void listar() {

        try {

            personas = personaON.listar();

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()));

            e.printStackTrace();

        }

    }

    public void buscar(String cedula) {

        try {

            persona = personaON.buscar(cedula);

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()));

        }

    }
    
    
    public void buscarCedula() {

        try {

            Persona p = personaON.buscarCedula(persona.getCedula());

            if (p != null) {

                persona = p;

            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Aviso",
                                "No existe una persona con esa cédula."));

            }

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()));

        }

    }
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

}