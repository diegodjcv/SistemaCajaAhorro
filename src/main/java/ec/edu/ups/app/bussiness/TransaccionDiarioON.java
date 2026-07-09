package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.TransaccionDiarioDao;
import ec.edu.ups.app.modelo.TransaccionDiario;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TransaccionDiarioON {

    @Inject
    private TransaccionDiarioDao diarioDAO;

    public void guardar(TransaccionDiario diario) {
        if (diario.getId() == 0)
            diarioDAO.guardar(diario);
        else
            diarioDAO.actualizar(diario);
    }

    public void eliminar(int codigo) {
        diarioDAO.eliminar(codigo);
    }

    public TransaccionDiario buscar(int codigo) {
        return diarioDAO.buscar(codigo);
    }

    public List<TransaccionDiario> listar() {
        return diarioDAO.listar();
    }
}