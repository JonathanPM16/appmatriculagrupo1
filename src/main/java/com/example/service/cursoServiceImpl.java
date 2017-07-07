package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.curso;
import com.example.repository.cursoRepository;

@Service
public class cursoServiceImpl  implements cursoService{

	@Autowired
	private cursoRepository cursorep;
	
	@Override
	public curso saveCurso(curso c) {
		// TODO Auto-generated method stub
		return cursorep.save(c);
	}

	@Override
	public Iterable<curso> listAllCurso() {
		// TODO Auto-generated method stub
		return cursorep.findAll();
	}

	@Override
	public void deleteCurso(int id) {
		// TODO Auto-generated method stub
		cursorep.delete(id);
	}

	@Override
	public curso getporccurso(int ccurso) {
		// TODO Auto-generated method stub
		return cursorep.findOne(ccurso);
	}

	@Override
	public curso validarcursorepetido(curso c) {
		// TODO Auto-generated method stub
		return cursorep.findByNombrecursoAndModalidadAndFacultadAndSede(c.getNombrecurso(), c.getModalidad(), c.getFacultad(), c.getSede());
	}

}
