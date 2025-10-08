package com.sistemaVeterinario;

public class Mascota {
	private String nombre;
	private String especie;
	private int edad;
	private Dueño dueño;
	
	public Mascota(String nombre, String especie, int edad, Dueño dueño) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.dueño = dueño;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Dueño getDueño() {
		return dueño;
	}

	public void setDueño(Dueño dueño) {
		this.dueño = dueño;
	}

	@Override
	public String toString() {
		return "Mascota [nombre=" + nombre + ", especie=" + especie + ", edad=" + edad + ", dueño=" + dueño + "]";
	}
	
	
}
