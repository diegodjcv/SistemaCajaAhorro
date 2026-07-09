package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.AsientoContableDao;
import ec.edu.ups.app.modelo.AsientoContable;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AsientoContableON {

    @Inject
    private AsientoContableDao asientoDAO;

    public void guardar(AsientoContable asiento) {
        if (asiento.getId() == 0)
            asientoDAO.actualizar(asiento);
        else
            asientoDAO.actualizar(asiento);
    }

    public void eliminar(int codigo) {
        asientoDAO.eliminar(codigo);
    }

    public AsientoContable buscar(int codigo) {
        return asientoDAO.buscar(codigo);
    }

    public List<AsientoContable> listar() {
        return asientoDAO.listar();
    }
}