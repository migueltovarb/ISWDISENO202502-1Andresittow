package com.sistemaVeterinario;

import java.util.Date;

public class ControlVeterinario {
	private Date fecha;
	private TipoControl control;
	private String observaciones;
	
	public ControlVeterinario(Date fecha, TipoControl control, String observaciones) {
		super();
		this.fecha = fecha;
		this.control = control;
		this.observaciones = observaciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoControl getControl() {
		return control;
	}

	public void setControl(TipoControl control) {
		this.control = control;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "ControlVeterinario [fecha=" + fecha + ", control=" + control + ", observaciones=" + observaciones + "]";
	}
}
