package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entities.alumno;
import com.example.service.alumnoService;

@Controller
public class alumnoController {

	@Autowired
	private alumnoService alumnoser;
	
	@RequestMapping(value="/nuevoalumno")
	public String alumnos(Model model){
		model.addAttribute("alumno", new alumno());
		model.addAttribute("alumnos",alumnoser.listAllAlumno());
		return "newAlumno";
	}
	
	@RequestMapping(value="/alumno/new", method=RequestMethod.POST)
	public String saveAlumno(@Valid alumno a, BindingResult result, Model model){
		
		try {
			if(result.hasErrors()){
			//	model.addAttribute("message", result.toString());
				model.addAttribute("alumnos",alumnoser.listAllAlumno());
				return "newAlumno";
			}
			alumnoser.saveAlumno(a);
			model.addAttribute("message", "Alumno registrado con exito");
			return "redirect:/nuevoalumno";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("alumnos",alumnoser.listAllAlumno());
			return "newAlumno";
		}
	}
	
	/*@RequestMapping(value="/alumnos")
	public String listalumnos(Model model){
		model.addAttribute("alumnos", alumnoser.listAllAlumno());
		return "alumnos";
	}*/ 
	
	@RequestMapping( value="/deletealumno{id}")
	public String deletealumno(@PathVariable int id, Model model)
	{
		try
		{
			alumnoser.deleteAlumno(id);
			return"redirect:/nuevoalumno";
		}
		catch (Exception e)
		{
			model.addAttribute("message", "ERROR: No se puede eliminar un alumno que está incluido dentro de alguna MATRÍCULA.");
			model.addAttribute("alumno", new alumno());
			model.addAttribute("alumnos",alumnoser.listAllAlumno());
			return "newAlumno";
		}
	}
	@RequestMapping(value="/editarAlumno{cal}",method=RequestMethod.GET)
	public String editarAlumno(@PathVariable int cal,Model model)
	{
		model.addAttribute("alumno", alumnoser.getporcalumno(cal));
		return "editarAlumno";
	}
}
