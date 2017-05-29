package com.example.service;

import java.util.List;

import com.example.entities.alumno;
import com.example.entities.matricula;
import com.example.entities.seccion;

public interface matriculaService {

	matricula saveMatricula(matricula m);
	Iterable<matricula> ListAllMatricula();
	void deleteMatricula(int id);
	matricula getbyCmatricula(int cmatricula);
	List<matricula> listarXSeccion(seccion s);
	List<matricula> listarXAlumno(alumno a);
}
