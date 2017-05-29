package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class admin {

	@Id
	private int cadmin;
	
	@Size(min=4,max=45)
	private String usuario;
	
	@Size(min=4,max=45)
	private String password;

	public int getCadmin() {
		return cadmin;
	}

	public void setCadmin(int cadmin) {
		this.cadmin = cadmin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
