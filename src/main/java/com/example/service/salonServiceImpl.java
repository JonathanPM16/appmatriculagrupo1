package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.salon;
import com.example.repository.salonRepository;

@Service
public class salonServiceImpl implements salonService
{
	@Autowired
	private salonRepository salonRep;

	@Override
	public salon saveSalon(salon s) 
	{
		return salonRep.save(s);
	}

	@Override
	public Iterable<salon> listAllSalon() 
	{
		return salonRep.findAll();
	}

	@Override
	public void deleteSalon(int id) 
	{
		salonRep.delete(id);
	}

	@Override
	public salon getByCSalon(int csalon) 
	{
		return salonRep.findOne(csalon);
	}
}
