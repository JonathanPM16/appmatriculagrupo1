package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entities.docente;
import com.example.service.docenteService;

@Controller
public class docenteController {

	@Autowired
	private docenteService docenteser;
	
	@RequestMapping(value="/nuevodocente")
	public String alumnos(Model model){
		model.addAttribute("docente", new docente());
		model.addAttribute("docentes",docenteser.listAllDocente());
		return "newDocente";
	}
	@RequestMapping(value="/docente/new", method=RequestMethod.POST)
	public String saveDocente(@Valid docente d, BindingResult result, Model model){
		
		try {
			if(result.hasErrors()){
				model.addAttribute("message", result.toString());
				model.addAttribute("docentes",docenteser.listAllDocente());
				return "newDocente";
			}
			docenteser.saveDocente(d);
			return "redirect:/nuevodocente";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("docentes",docenteser.listAllDocente());
			return "newDocente";
		}
	}
	@RequestMapping( value="/deletedocente{id}")
	public String deletedocente(@PathVariable int id, Model model)
	{
		try
		{
			docenteser.deleteDocente(id);
			return"redirect:/nuevodocente";
		}
		catch (Exception e)
		{
			model.addAttribute("message", "ERROR: No se puede eliminar un docente que está incluido dentro de alguna SECCIÓN.");
			model.addAttribute("docente", new docente());
			model.addAttribute("docentes",docenteser.listAllDocente());
			return "newDocente";
		}
	}
	@RequestMapping(value="/editarDocente{cdoc}",method=RequestMethod.GET)
	public String editarAlumno(@PathVariable int cdoc,Model model)
	{
		model.addAttribute("docente", docenteser.getporcdocente(cdoc));
		return "editarDocente";
	}
	
	
}
