package com.example.service;

import com.example.entities.alumno;


public interface alumnoService {

	alumno saveAlumno(alumno a);
	
	Iterable<alumno> listAllAlumno();
	void deleteAlumno(int id);
	alumno getporcalumno(int calumno);
	alumno getporDNI(String dni);

}
