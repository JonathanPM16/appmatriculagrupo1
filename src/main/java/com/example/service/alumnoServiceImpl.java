package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.alumno;
import com.example.repository.alumnoRepository;

@Service
public class alumnoServiceImpl implements alumnoService {

	@Autowired
	private alumnoRepository alumnorep;
	
	
	@Override
	public alumno saveAlumno(alumno a) {
		// TODO Auto-generated method stub
		return alumnorep.save(a);
	}

	@Override
	public Iterable<alumno> listAllAlumno() {
		// TODO Auto-generated method stub
		return alumnorep.findAll();
	}

	@Override
	public void deleteAlumno(int id) {
		// TODO Auto-generated method stub
		alumnorep.delete(id);
	}

	@Override
	public alumno getporcalumno(int calumno) {
		// TODO Auto-generated method stub
		return alumnorep.findOne(calumno);
	}

	@Override
	public alumno getporDNI(String dni) {
		// TODO Auto-generated method stub
		return alumnorep.findByDni(dni);
	}




	
	
}
