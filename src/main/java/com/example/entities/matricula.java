package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class matricula {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cmatricula;
	
	@ManyToOne
	@JoinColumn(name="cseccion")
	private seccion seccion;
	
	@ManyToOne
	@JoinColumn(name="calumno")
	private alumno alumno;

	public int getCmatricula() {
		return cmatricula;
	}

	public void setCmatricula(int cmatricula) {
		this.cmatricula = cmatricula;
	}

	public seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(seccion seccion) {
		this.seccion = seccion;
	}

	public alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(alumno alumno) {
		this.alumno = alumno;
	}
	
}
