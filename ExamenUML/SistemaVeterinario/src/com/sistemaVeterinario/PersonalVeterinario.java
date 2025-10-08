package com.sistemaVeterinario;

import java.util.List;
import java.util.ArrayList;

public class PersonalVeterinario {
	private List<Dueño> listaDueños;
	private List<Mascota> listaMascotas;
	private List<ControlVeterinario> listaControles;
	
	
	
	public PersonalVeterinario() {
		super();
		this.listaDueños = new ArrayList<>();
		this.listaMascotas = new ArrayList<>();
		this.listaControles = new ArrayList<>();
	}

	public List<Dueño> getListaDueños() {
		return listaDueños;
	}
	
	public void setListaDueños(List<Dueño> listaDueños) {
		this.listaDueños = listaDueños;
	}
	
	public List<Mascota> getListaMascotas() {
		return listaMascotas;
	}
	
	public void setListaMascotas(List<Mascota> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}
	
	public List<ControlVeterinario> getControl() {
		return listaControles;
	}
	
	public void setControl(List<ControlVeterinario> control) {
		this.listaControles = control;
	}
	
	public void registrarDueño(Dueño dueño) {
		for (Dueño d : listaDueños) {
			if (d.getDocumento().equals(dueño.getDocumento())) {
				System.out.println("El dueño con el documento " + d.getDocumento() + " ya existe.");
				return;
			}
		}
		listaDueños.add(dueño);
		System.out.println("El dueño se ha registrado de manera exitosa!");
	}
	
	public void registrarMascota(Mascota mascota) {
		if (! listaDueños.contains(mascota.getDueño())) {
			System.out.println("El dueño no está registrado");
			return;
		}
		
		for (Mascota m : listaMascotas) {
			if (m.getNombre().equalsIgnoreCase(mascota.getNombre()) && m.getDueño().equals(mascota.getDueño())) {
				System.out.println("Ya existe una mascota con ese nombre asignado a su dueño");
				return;
			}
		}
		listaMascotas.add(mascota);
		System.out.println("La mascota se ha registrado exitosamente!");
	}
	
	public void registrarControl(ControlVeterinario control, Mascota mascota) {
		if (! listaMascotas.contains(mascota)) {
			System.out.println("La mascota aún no está registrada en el sistema.");
			return;
		}
		listaControles.add(control);
		System.out.println("Control registrado para " + mascota.getNombre());
	}
	
	public List<ControlVeterinario> consultarHistorialMedico(Mascota mascota) {
		List<ControlVeterinario> historial = new ArrayList<>();
		for (ControlVeterinario c : listaControles) {
			historial.add(c);
		}
		return historial;
	}
	
	public String resumenMascota(Mascota mascota) {
		int cantidadControles = consultarHistorialMedico(mascota).size();
		return "Mascota: " + mascota.getNombre() + "\nEspecie: " + mascota.getEspecie() + "\nControles registrados : " + cantidadControles;
	} 
}
