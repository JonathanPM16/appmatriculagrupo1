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
		if(docenteser.listAllDocente().spliterator().getExactSizeIfKnown()==0){
			model.addAttribute("messagevacio", "NO HAY REGISTROS");
		}
		return "newDocente";
	}
	
	@RequestMapping(value="/docente/new", method=RequestMethod.POST)
	public String saveDocente(@Valid docente d, BindingResult result, Model model, RedirectAttributes ra){
		
		try {
			if(result.hasErrors()){
				//model.addAttribute("message", result.toString());
				model.addAttribute("docentes",docenteser.listAllDocente());
				if(docenteser.listAllDocente().spliterator().getExactSizeIfKnown()==0){
					model.addAttribute("messagevacio", "NO HAY REGISTROS");
				}
				return "newDocente";
			}else if (docenteser.getporDNI(d.getDni())!=null) {
				model.addAttribute("messagedni", "Este DNI ya le pertenece a un docente dentro de la base de datos");
				model.addAttribute("docentes",docenteser.listAllDocente());
				if(docenteser.listAllDocente().spliterator().getExactSizeIfKnown()==0){
					model.addAttribute("messagevacio", "NO HAY REGISTROS");
				}
				return "newDocente";
			}else if(d.getNombredocente().substring(0,1).equals(" ") || d.getApellidodocente().substring(0,1).equals(" ") ||
					 d.getDescripcion().substring(0,1).equals(" ")){
				model.addAttribute("docentes",docenteser.listAllDocente());
				model.addAttribute("messageespacios", "No use espacios para iniciar los campos");
				if(docenteser.listAllDocente().spliterator().getExactSizeIfKnown()==0){
					model.addAttribute("messagevacio", "NO HAY REGISTROS");
				}
				return "newDocente";
			}
			docenteser.saveDocente(d);
			ra.addFlashAttribute("messageregistro", "Docente registrado con exito");
			return "redirect:/nuevodocente";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("docentes",docenteser.listAllDocente());
			if(docenteser.listAllDocente().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newDocente";
		}
	}
	
	@RequestMapping(value="/docente/update", method=RequestMethod.POST)
	public String updateDocente(@Valid docente d, BindingResult result, Model model, RedirectAttributes ra){
		
		try {
			if(result.hasErrors()){
				//model.addAttribute("message", result.toString());
				model.addAttribute("docentes",docenteser.listAllDocente());
				return "editarDocente";
			}else if(d.getNombredocente().substring(0,1).equals(" ") || d.getApellidodocente().substring(0,1).equals(" ") ||
					 d.getDescripcion().substring(0,1).equals(" ")){
				model.addAttribute("messageespacios", "No use espacios para iniciar los campos");
				if(docenteser.listAllDocente().spliterator().getExactSizeIfKnown()==0){
					model.addAttribute("messagevacio", "NO HAY REGISTROS");
				}
				return "editarDocente";
			}
			docenteser.saveDocente(d);
			ra.addFlashAttribute("messageeditado", "Docente editado con exito");
			return "redirect:/nuevodocente";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("docentes",docenteser.listAllDocente());
			return "editarDocente";
		}
	}
	
	@RequestMapping( value="/deletedocente{id}")
	public String deletedocente(@PathVariable int id, Model model, RedirectAttributes ra)
	{
		try
		{
			docenteser.deleteDocente(id);
			ra.addFlashAttribute("messageexito", "Docente eliminado del sistema con exito");
			return"redirect:/nuevodocente";
		}
		catch (Exception e)
		{
			model.addAttribute("message", "ERROR: No se puede eliminar un docente que está incluido dentro de alguna SECCIÓN.");
			model.addAttribute("docente", new docente());
			model.addAttribute("docentes",docenteser.listAllDocente());
			if(docenteser.listAllDocente().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
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
