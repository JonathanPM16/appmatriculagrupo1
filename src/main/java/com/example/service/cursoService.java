package com.example.service;

import com.example.entities.curso;

public interface cursoService {

	curso saveCurso(curso c);
	Iterable<curso> listAllCurso();
	void deleteCurso(int id);
	curso getporccurso(int ccurso);
	curso validarcursorepetido(curso c);
}
