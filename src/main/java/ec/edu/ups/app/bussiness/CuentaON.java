package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.CuentaDao;
import ec.edu.ups.app.modelo.Cuenta;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CuentaON {

    @Inject
    private CuentaDao cuentaDAO;

    public void guardar(Cuenta cuenta) {
        if (cuenta.getId() == 0)
            cuentaDAO.guardar(cuenta);
        else
            cuentaDAO.actualizar(cuenta);
    }

    public void eliminar(int codigo) {
        cuentaDAO.eliminar(codigo);
    }

    public Cuenta buscar(int codigo) {
        return cuentaDAO.buscar(codigo);
    }

    public List<Cuenta> listar() {
        return cuentaDAO.listar();
    }
}