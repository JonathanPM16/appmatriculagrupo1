package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.salon;

@Repository
@Transactional
public interface salonRepository extends CrudRepository<salon, Integer>
{

	salon findByNombre(int nombre);
}
