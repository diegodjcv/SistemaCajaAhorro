package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.TipoTransaccionDao;
import ec.edu.ups.app.modelo.TipoTransaccion;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TipoTransaccionON {

    @Inject
    private TipoTransaccionDao tipoDAO;

    public void guardar(TipoTransaccion tipo) {
        if (tipo.getId() == 0)
            tipoDAO.insertar(tipo);
        else
            tipoDAO.actualizar(tipo);
    }

    public void eliminar(int codigo) {
        tipoDAO.eliminar(codigo);
    }

    public TipoTransaccion buscar(int codigo) {
        return tipoDAO.buscar(codigo);
    }

    public List<TipoTransaccion> listar() {
        return tipoDAO.listar();
    }
}