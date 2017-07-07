package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class alumno {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int calumno;
	
	@Size(min=2, max=20,message="El nombre del alumno debe contener entre 2 a 20 caracteres")
    private String nombrealumno;
	@Size(min=2, max=20,message="El apellido del alumno debe contener entre 2 a 20 caracteres")
    private String apellidoalumno;
    
    @Size(min=6, max=6)
    private String periodoingreso;
    
    @Size(min=8, max=8,message="El DNI debe contener unicamente 8 cifras")
    private String dni;
    
    @Size(min=2, max=20,message="El colegio debe contener entre 2 a 20 caracteres")
    private String colegio;
    
    @Size(min=2, max=45)
    private String carrera;
    
    @Size(min=10, max=10,message="Coloca una fecha")
    private String fnacimientoalumno;
    
    @Size(min=8, max=9,message="Selecciona un genero")
    private String genero;
    
	public int getCalumno() {
		return calumno;
	}
	public void setCalumno(int calumno) {
		this.calumno = calumno;
	}
	public String getNombrealumno() {
		return nombrealumno;
	}
	public void setNombrealumno(String nombrealumno) {
		this.nombrealumno = nombrealumno;
	}
	public String getApellidoalumno() {
		return apellidoalumno;
	}
	public void setApellidoalumno(String apellidoalumno) {
		this.apellidoalumno = apellidoalumno;
	}
	public String getPeriodoingreso() {
		return periodoingreso;
	}
	public void setPeriodoingreso(String periodoingreso) {
		this.periodoingreso = periodoingreso;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getColegio() {
		return colegio;
	}
	public void setColegio(String colegio) {
		this.colegio = colegio;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getFnacimientoalumno() {
		return fnacimientoalumno;
	}
	public void setFnacimientoalumno(String fnacimientoalumno) {
		this.fnacimientoalumno = fnacimientoalumno;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
    
    
}
