package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.RolDao;
import ec.edu.ups.app.modelo.Rol;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RolON {

    @Inject
    private RolDao rolDAO;

    public void guardar(Rol rol) {
        if (rol.getId() == 0)
            rolDAO.buscar(rol.getId());
        else
            rolDAO.actualizar(rol);
    }

    public void eliminar(int codigo) {
        rolDAO.eliminar(codigo);
    }

    public Rol buscar(int codigo) {
        return rolDAO.buscar(codigo);
    }

    public List<Rol> listar() {
        return rolDAO.listar();
    }
}