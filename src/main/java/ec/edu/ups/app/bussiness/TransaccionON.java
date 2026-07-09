package ec.edu.ups.app.bussiness;

import ec.edu.ups.app.dao.TransaccionDao;
import ec.edu.ups.app.modelo.Transaccion;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TransaccionON {

    @Inject
    private TransaccionDao transaccionDAO;

    public void guardar(Transaccion transaccion) {
        if (transaccion.getId() == 0)
            transaccionDAO.insertar(transaccion);
        else
            transaccionDAO.actualizar(transaccion);
    }

    public void eliminar(int codigo) {
        transaccionDAO.eliminar(codigo);
    }

    public Transaccion buscar(int codigo) {
        return transaccionDAO.buscar(codigo);
    }

    public List<Transaccion> listar() {
        return transaccionDAO.listar();
    }
}