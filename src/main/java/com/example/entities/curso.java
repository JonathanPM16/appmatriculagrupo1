package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class curso {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ccurso;
	
	@Size(min=2,max=70,message="El nombre del curso debe contener entre 2 a 70 caracteres")
	private String nombrecurso;
	
	@NotNull(message="Elige una modalidad")
	@Size(min=2,max=10,message="Elige una modalidad")
	private String modalidad;
	
	@Size(min=2,max=50,message="Elige una facultad")
	private String facultad;
	
	@NotNull(message="Elige una sede")
	@Size(min=2,max=50,message="Elige una sede")
	private String sede;
	
	@Size(min=7,max=45,message="La descripción debe contener entre 7 a 45 caracteres")
	
	private String descripcion;
	public int getCcurso() {
		return ccurso;
	}
	public void setCcurso(int ccurso) {
		this.ccurso = ccurso;
	}
	public String getNombrecurso() {
		return nombrecurso;
	}
	public void setNombrecurso(String nombrecurso) {
		this.nombrecurso = nombrecurso;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
