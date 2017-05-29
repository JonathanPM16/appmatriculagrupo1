package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class seccion {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private int cseccion;
   
   @Size(min=4,max=4)
   private String nombreseccion;
   
   private String ciclo;
   
   @ManyToOne
   @JoinColumn(name="ccurso")
   private curso curso;
   
   @ManyToOne
   @JoinColumn(name="cdocente")
   private docente docente;
   
   @Min(0)
   @Max(30)
   private int nummatriculados;

public int getCseccion() {
	return cseccion;
}

public void setCseccion(int cseccion) {
	this.cseccion = cseccion;
}

public String getNombreseccion() {
	return nombreseccion;
}

public void setNombreseccion(String nombreseccion) {
	this.nombreseccion = nombreseccion;
}

public String getCiclo() {
	return ciclo;
}

public void setCiclo(String ciclo) {
	this.ciclo = ciclo;
}

public curso getCurso() {
	return curso;
}

public void setCurso(curso curso) {
	this.curso = curso;
}

public docente getDocente() {
	return docente;
}

public void setDocente(docente docente) {
	this.docente = docente;
}

public int getNummatriculados() {
	return nummatriculados;
}

public void setNummatriculados(int nummatriculados) {
	this.nummatriculados = nummatriculados;
}
   
   
    
   
}
