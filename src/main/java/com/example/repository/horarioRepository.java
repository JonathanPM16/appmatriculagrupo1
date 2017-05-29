package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.horario;
import com.example.entities.seccion;

@Repository
@Transactional
public interface horarioRepository extends CrudRepository<horario, Integer> {
	List<horario> findByDia(String dia);
	List<horario> findBySeccion(seccion seccion);
}
