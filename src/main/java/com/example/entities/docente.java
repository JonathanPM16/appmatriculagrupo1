package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class docente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cdocente;
	
	@Size(min=2, max=20)
	private String nombredocente;
	
	@Size(min=2, max=20)
	private String apellidodocente;
	
	 @Size(min=8, max=8)
	private String dni;
	
	 @Size(min=10, max=10)
	private String fnacimiento;
	
	 @Size(min=5, max=200)
	private String descripcion;

	public int getCdocente() {
		return cdocente;
	}

	public void setCdocente(int cdocente) {
		this.cdocente = cdocente;
	}

	public String getNombredocente() {
		return nombredocente;
	}

	public void setNombredocente(String nombredocente) {
		this.nombredocente = nombredocente;
	}

	public String getApellidodocente() {
		return apellidodocente;
	}

	public void setApellidodocente(String apellidodocente) {
		this.apellidodocente = apellidodocente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFnacimiento() {
		return fnacimiento;
	}

	public void setFnacimiento(String fnacimiento) {
		this.fnacimiento = fnacimiento;
	}
	
	
}
