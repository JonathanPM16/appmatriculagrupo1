package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.docente;

@Repository
@Transactional
public interface docenteRepository extends CrudRepository<docente, Integer> {

	docente findByDni(String dni);
}
