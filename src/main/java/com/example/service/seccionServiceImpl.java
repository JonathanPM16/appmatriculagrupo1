package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.seccion;
import com.example.repository.seccionRepository;

@Service
public class seccionServiceImpl implements seccionService {

	@Autowired
	private seccionRepository seccionRep;

	@Override
	public seccion saveSeccion(seccion s) {
		// TODO Auto-generated method stub
		return seccionRep.save(s);
	}

	@Override
	public Iterable<seccion> listAllSeccion() {
		// TODO Auto-generated method stub
		return seccionRep.findAll();
	}

	@Override
	public void deleteSeccion(int id) {
		// TODO Auto-generated method stub
		seccionRep.delete(id);
	}

	@Override
	public Iterable<seccion> listarSeccionxCiclo(String ciclo) {
		// TODO Auto-generated method stub
		return seccionRep.findByCiclo(ciclo);
	}

	@Override
	public seccion GetSeccion(int cseccion) {
		// TODO Auto-generated method stub
		return seccionRep.findOne(cseccion);
	}

	@Override
	public Iterable<seccion> listarSeccionIgual(int num) {
		// TODO Auto-generated method stub
		return seccionRep.findByNummatriculados(num);
	}

	@Override
	public Iterable<seccion> listarSeccionMenor(int num) {
		// TODO Auto-generated method stub
		return seccionRep.findByNummatriculadosLessThan(num);
	}
	
	
}
