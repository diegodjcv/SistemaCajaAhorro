package ec.edu.ups.app.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cuota {

	@Id
	private int id;
	private double monto;
	private Date fechaVencimiento;
	private Date fechaPago;
	private String estado;
	private double montoInteres;
	private double montoPago;
	
	@OneToOne
	private Credito credito;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getMontoInteres() {
		return montoInteres;
	}

	public void setMontoInteres(double montoInteres) {
		this.montoInteres = montoInteres;
	}

	public double getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(double montoPago) {
		this.montoPago = montoPago;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}
	
	
	
}
