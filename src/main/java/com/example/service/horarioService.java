package com.example.service;

import com.example.entities.horario;
import com.example.entities.seccion;

public interface horarioService {

	horario saveHorario(horario h);
	Iterable<horario> ListAllHorario();
	void deleteHorario(int id);
	horario getHorario(int chorario);
	Iterable<horario> ListHorarioXDia(String dia);
	Iterable<horario> ListHorarioXSeccion(seccion seccion);
	
}
