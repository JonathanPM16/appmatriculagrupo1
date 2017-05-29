package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.admin;

@Repository
@Transactional
public interface adminRepository extends CrudRepository<admin, Integer> {

	admin findByUsuario(String usuario);
}
