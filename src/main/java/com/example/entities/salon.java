package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class salon 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int csalon;
	
	@Min(100)
	@Max(999)
	private int nombre;
	
	@Size(min=2, max=45)
	private String descripcion;
	
	public int getCsalon() 
	{
		return csalon;
	}
	
	public void setCsalon(int csalon) 
	{
		this.csalon = csalon;
	}
	
	
	
	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
}