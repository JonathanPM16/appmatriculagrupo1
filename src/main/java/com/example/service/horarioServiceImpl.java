package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.horario;
import com.example.entities.seccion;
import com.example.repository.horarioRepository;


@Service
public class horarioServiceImpl implements horarioService {

	@Autowired
	private horarioRepository horarioRep;
	
	@Override
	public horario saveHorario(horario h) {
		// TODO Auto-generated method stub
		return horarioRep.save(h);
	}

	@Override
	public Iterable<horario> ListAllHorario() {
		// TODO Auto-generated method stub
		return horarioRep.findAll();
	}

	@Override
	public void deleteHorario(int id) {
		// TODO Auto-generated method stub
		horarioRep.delete(id);
	}

	@Override
	public horario getHorario(int chorario) {
		// TODO Auto-generated method stub
		return horarioRep.findOne(chorario);
	}

	@Override
	public Iterable<horario> ListHorarioXDia(String dia) {
		// TODO Auto-generated method stub
		return horarioRep.findByDia(dia);
	}

	@Override
	public Iterable<horario> ListHorarioXSeccion(seccion seccion) {
		// TODO Auto-generated method stub
		return horarioRep.findBySeccion(seccion);
	}

}
