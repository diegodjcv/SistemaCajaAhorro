package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.CreditoDao;
import ec.edu.ups.app.modelo.Credito;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CreditoON {

    @Inject
    private CreditoDao creditoDAO;

    public void guardar(Credito credito) {
        if (credito.getId() == 0)
            creditoDAO.solicitarCredito(credito);
        else
            creditoDAO.actualizar(credito);
    }

    public void eliminar(int codigo) {
        creditoDAO.eliminar(codigo);
    }

    public Credito buscar(int codigo) {
        return creditoDAO.buscar(codigo);
    }

    public List<Credito> listar() {
        return creditoDAO.listar();
    }
}