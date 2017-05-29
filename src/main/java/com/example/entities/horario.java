package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class horario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int chorario;
	
	private String dia;
	
	@Min(7)
	@Max(22)
	private int horainicio;
	
	@Min(8)
	@Max(23)
	private int horafin;
	
	@ManyToOne
	@JoinColumn(name="cseccion")
	private seccion seccion;
	
	@ManyToOne
	@JoinColumn(name="csalon")
	private salon salon;

	public int getChorario() {
		return chorario;
	}

	public void setChorario(int chorario) {
		this.chorario = chorario;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public int getHorainicio() {
		return horainicio;
	}

	public void setHorainicio(int horainicio) {
		this.horainicio = horainicio;
	}

	public int getHorafin() {
		return horafin;
	}

	public void setHorafin(int horafin) {
		this.horafin = horafin;
	}

	public seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(seccion seccion) {
		this.seccion = seccion;
	}

	public salon getSalon() {
		return salon;
	}

	public void setSalon(salon salon) {
		this.salon = salon;
	}
	
	
}
