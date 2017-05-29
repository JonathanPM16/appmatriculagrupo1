package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.alumno;
import com.example.entities.matricula;
import com.example.entities.seccion;

@Repository
@Transactional
public interface matriculaRepository extends CrudRepository<matricula, Integer> {
	List<matricula> findBySeccion(seccion s);
	List<matricula> findByAlumno(alumno a);
}
