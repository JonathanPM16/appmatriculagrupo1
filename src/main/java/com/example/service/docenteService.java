package com.example.service;

import com.example.entities.docente;

public interface docenteService {

	docente saveDocente(docente d);
	Iterable<docente> listAllDocente();
	void deleteDocente(int id);
	docente getporcdocente(int cdocente);
	docente getporDNI(String dni);
}
