package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.PersonaDao;
import ec.edu.ups.app.modelo.Persona;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PersonaON {

    @Inject
    private PersonaDao personaDAO;

    public void guardar(Persona persona) {
        if (persona.getId() == 0)
            personaDAO.insertar(persona);
        else
            personaDAO.actualizar(persona);
    }

    public void eliminar(String cedula) {
        personaDAO.eliminar(cedula);
    }

    public Persona buscar(int codigo) {
        return personaDAO.buscar(codigo);
    }

    public Persona buscarCedula(String cedula) {
        return personaDAO.buscarPorCedula(cedula);
    }

    public List<Persona> listar() {
        return personaDAO.listar();
    }
}