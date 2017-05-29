package com.example.service;

import com.example.entities.salon;

public interface salonService 
{
	salon saveSalon(salon s);
	Iterable<salon> listAllSalon();
	void deleteSalon(int id);
	salon getByCSalon(int csalon);
}
