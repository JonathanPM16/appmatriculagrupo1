package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		if(alumnoser.listAllAlumno().spliterator().getExactSizeIfKnown()==0){
			model.addAttribute("messagevacio", "NO HAY REGISTROS");
		}
		return "newAlumno";
	}
	
	@RequestMapping(value="/alumno/new", method=RequestMethod.POST)
	public String saveAlumno(@Valid alumno a, BindingResult result, Model model, RedirectAttributes ra){
		
		try {
			if(result.hasErrors()){
			//	model.addAttribute("message", result.toString());
				model.addAttribute("alumnos",alumnoser.listAllAlumno());
				if(alumnoser.listAllAlumno().spliterator().getExactSizeIfKnown()==0){
					model.addAttribute("messagevacio", "NO HAY REGISTROS");
				}
				return "newAlumno";
			}else if (alumnoser.getporDNI(a.getDni())!=null) {
				model.addAttribute("messagedni", "Este DNI ya le pertenece a un alumno dentro de la base de datos");
				model.addAttribute("alumnos",alumnoser.listAllAlumno());	
				return "newAlumno";
			}
			alumnoser.saveAlumno(a);
			ra.addFlashAttribute("messageregistro", "Alumno registrado con exito");	
			return "redirect:/nuevoalumno";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("alumnos",alumnoser.listAllAlumno());
			if(alumnoser.listAllAlumno().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newAlumno";
		}
	}
	
	@RequestMapping(value="/alumno/update", method=RequestMethod.POST)
	public String updateAlumno(@Valid alumno a, BindingResult result, Model model, RedirectAttributes ra){
		
		try {
			if(result.hasErrors()){
			//	model.addAttribute("message", result.toString());
				model.addAttribute("alumnos",alumnoser.listAllAlumno());
				return "editarAlumno";
			}
			alumnoser.saveAlumno(a);
			ra.addFlashAttribute("messageeditado", "Alumno editado con exito");
			return "redirect:/nuevoalumno";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("alumnos",alumnoser.listAllAlumno());
			return "editarAlumno";
		}
	}

	@RequestMapping( value="/deletealumno{id}")
	public String deletealumno(@PathVariable int id, Model model, RedirectAttributes ra)
	{
		try
		{
			alumnoser.deleteAlumno(id);
			ra.addFlashAttribute("messageexito", "Alumno eliminado del sistema con exito");
			return"redirect:/nuevoalumno";
		}
		catch (Exception e)
		{
			model.addAttribute("messageerror", "ERROR: No se puede eliminar un alumno que está incluido dentro de alguna MATRÍCULA.");
			model.addAttribute("alumno", new alumno());
			model.addAttribute("alumnos",alumnoser.listAllAlumno());
			if(alumnoser.listAllAlumno().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
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
