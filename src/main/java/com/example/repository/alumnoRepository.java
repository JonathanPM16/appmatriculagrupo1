package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.alumno;

@Repository
@Transactional
public interface alumnoRepository extends CrudRepository<alumno, Integer> {

	
}
