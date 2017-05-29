package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.admin;
import com.example.repository.adminRepository;

@Service
public class adminServiceImpl implements adminService {

	@Autowired
	private adminRepository adminRep;
	
	@Override
	public admin autenticarAdmin(String usuario, String password) {
		// TODO Auto-generated method stub
		admin admin = adminRep.findByUsuario(usuario);
		if (admin!=null && admin.getPassword().equals(password)) {
			return admin;
		}
		return null;
	}

}
