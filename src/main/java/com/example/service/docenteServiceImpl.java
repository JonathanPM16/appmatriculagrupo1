package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.docente;
import com.example.repository.docenteRepository;

@Service
public class docenteServiceImpl implements docenteService {

	@Autowired
	private docenteRepository docenterep;

	@Override
	public docente saveDocente(docente d) {
		// TODO Auto-generated method stub
		return docenterep.save(d);
	}

	@Override
	public Iterable<docente> listAllDocente() {
		// TODO Auto-generated method stub
		return docenterep.findAll();
	}

	@Override
	public void deleteDocente(int id) {
		// TODO Auto-generated method stub
		docenterep.delete(id);
	}

	@Override
	public docente getporcdocente(int cdocente) {
		// TODO Auto-generated method stub
		return docenterep.findOne(cdocente);
	}

	@Override
	public docente getporDNI(String dni) {
		// TODO Auto-generated method stub
		return docenterep.findByDni(dni);
	}
	
}
