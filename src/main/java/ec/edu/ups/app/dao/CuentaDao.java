package ec.edu.ups.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ec.edu.ups.app.modelo.Cuenta;

public class CuentaDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    // Guardar una cuenta
    public void insertar(Cuenta cuenta) {
        em.persist(cuenta);
    }

    // Actualizar una cuenta
    public void actualizar(Cuenta cuenta) {
        em.merge(cuenta);
    }

    // Eliminar una cuenta
    public void eliminar(int id) {
        Cuenta cuenta = em.find(Cuenta.class, id);

        if (cuenta != null) {
            em.remove(cuenta);
        }
    }

    // Buscar por ID
    public Cuenta buscar(int id) {
        return em.find(Cuenta.class, id);
    }

    // Listar todas las cuentas
    public List<Cuenta> listar() {
        String jpql = "SELECT c FROM Cuenta c";
        return em.createQuery(jpql, Cuenta.class).getResultList();
    }

    // Buscar por número de cuenta
    public Cuenta buscarNumeroCuenta(int numeroCuenta) {

        try {

            String jpql = "SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numero";

            TypedQuery<Cuenta> query = em.createQuery(jpql, Cuenta.class);
            query.setParameter("numero", numeroCuenta);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }

    // Consultar saldo
    public double consultarSaldo(int numeroCuenta) {

        Cuenta cuenta = buscarNumeroCuenta(numeroCuenta);

        if (cuenta != null) {
            return cuenta.getSaldo();
        }

        return 0;
    }

    // Depositar dinero
    public boolean depositar(int numeroCuenta, double monto) {

        Cuenta cuenta = buscarNumeroCuenta(numeroCuenta);

        if (cuenta != null && monto > 0) {

            cuenta.setSaldo(cuenta.getSaldo() + monto);

            em.merge(cuenta);

            return true;
        }

        return false;
    }

    // Retirar dinero
    public boolean retirar(int numeroCuenta, double monto) {

        Cuenta cuenta = buscarNumeroCuenta(numeroCuenta);

        if (cuenta != null &&
                monto > 0 &&
                cuenta.getSaldo() >= monto) {

            cuenta.setSaldo(cuenta.getSaldo() - monto);

            em.merge(cuenta);

            return true;
        }

        return false;
    }

    // Actualizar saldo
    public void actualizarSaldo(Cuenta cuenta) {
        em.merge(cuenta);
    }

}
