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
	
	@Size(min=2, max=20,message="El nombre del docente debe contener entre 2 a 20 caracteres")
	private String nombredocente;
	
	@Size(min=2, max=20,message="El apellido del docente debe contener entre 2 a 20 caracteres")
	private String apellidodocente;
	
	 @Size(min=8, max=8,message="El DNI debe contener unicamente 8 digitos")
	private String dni;
	
	 @Size(min=10, max=10,message="Ingrese una fecha de nacimiento")
	private String fnacimiento;
	
	 @Size(min=5, max=200,message="La descripcion debe contener entre 5 a 200 caracteres")
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
