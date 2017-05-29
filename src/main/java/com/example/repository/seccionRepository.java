package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.seccion;

@Repository
@Transactional
public interface seccionRepository extends CrudRepository<seccion, Integer>{

	List<seccion> findByNummatriculados(int num);
	List<seccion> findByNummatriculadosLessThan(int num);
	@Query("select s from seccion s where s.ciclo=?1")
	List<seccion> findByCiclo(String ciclo);
}
