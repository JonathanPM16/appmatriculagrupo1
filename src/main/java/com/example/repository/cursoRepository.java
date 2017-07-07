package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.curso;

@Repository
@Transactional
public interface cursoRepository extends CrudRepository<curso, Integer>{

	curso findByNombrecursoAndModalidadAndFacultadAndSede(String nombrecurso, String modalidad, String facultad, String sede);
}
