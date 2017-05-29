package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.alumno;
import com.example.entities.matricula;
import com.example.entities.seccion;
import com.example.repository.matriculaRepository;

@Service
public class matriculaServiceImpl implements matriculaService {

	@Autowired
	private matriculaRepository matriculaRep;
	
	@Override
	public matricula saveMatricula(matricula m) {
		// TODO Auto-generated method stub
		return matriculaRep.save(m);
	}

	@Override
	public Iterable<matricula> ListAllMatricula() {
		// TODO Auto-generated method stub
		return matriculaRep.findAll();
	}

	@Override
	public void deleteMatricula(int id) {
		// TODO Auto-generated method stub
		matriculaRep.delete(id);
	}

	@Override
	public matricula getbyCmatricula(int cmatricula) {
		// TODO Auto-generated method stub
		return matriculaRep.findOne(cmatricula);
	}

	@Override
	public List<matricula> listarXSeccion(seccion s) {
		// TODO Auto-generated method stub
		return matriculaRep.findBySeccion(s);
	}

	@Override
	public List<matricula> listarXAlumno(alumno a) {
		// TODO Auto-generated method stub
		return matriculaRep.findByAlumno(a);
	}

}
