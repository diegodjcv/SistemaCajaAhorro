package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.SocioDao;
import ec.edu.ups.app.modelo.Socio;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SocioON {

    @Inject
    private SocioDao socioDAO;

    public void guardar(Socio socio) {
        if (socio.getId() == 0)
            socioDAO.guardar(socio);
        else
            socioDAO.actualizar(socio);
    }

    public void eliminar(int codigo) {
        socioDAO.eliminar(codigo);
    }

    public Socio buscar(String cedula) {
        return socioDAO.buscarPorCedula(cedula);
    }

    public List<Socio> listar() {
        return socioDAO.listar();
    }
}