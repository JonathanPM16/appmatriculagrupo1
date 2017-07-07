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

import com.example.entities.seccion;
import com.example.service.cursoService;
import com.example.service.docenteService;
import com.example.service.seccionService;

@Controller
public class seccionController {

	@Autowired
	private seccionService seccionSer;
	
	@Autowired
	private docenteService docenteSer;
	
	@Autowired
	private cursoService cursoSer;
	
	@RequestMapping(value="/nuevaseccion")
	public String secciones(Model model){
		model.addAttribute("docentes", docenteSer.listAllDocente());
		model.addAttribute("cursos", cursoSer.listAllCurso());
		model.addAttribute("seccion", new seccion());
		model.addAttribute("secciones", seccionSer.listAllSeccion());
		if(seccionSer.listAllSeccion().spliterator().getExactSizeIfKnown()==0){
			model.addAttribute("messagevacio", "NO HAY REGISTROS");
		}
		return "newSeccion";
	}
	
	@RequestMapping(value="/seccion/new", method=RequestMethod.POST)
	public String saveSeccion(@Valid seccion s, BindingResult result, Model model, RedirectAttributes ra){
	   try {
		if (result.hasErrors()) {
			//model.addAttribute("message", result.toString());
			model.addAttribute("docentes", docenteSer.listAllDocente());
			model.addAttribute("cursos", cursoSer.listAllCurso());
			model.addAttribute("secciones", seccionSer.listAllSeccion());
			if(seccionSer.listAllSeccion().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newSeccion";
		}else if(s.getCurso()== null || s.getDocente()==null){
			model.addAttribute("message", "Por favor complete todos los campos");
			model.addAttribute("docentes", docenteSer.listAllDocente());
			model.addAttribute("cursos", cursoSer.listAllCurso());
			model.addAttribute("secciones", seccionSer.listAllSeccion());
			if(seccionSer.listAllSeccion().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newSeccion";
		}
		seccionSer.saveSeccion(s);
		ra.addFlashAttribute("messageregistro", "Seccion registrada con exito");
		return "redirect:/nuevaseccion";
	} catch (Exception e) {
		// TODO: handle exception
		model.addAttribute("message", e.getMessage());
		model.addAttribute("docentes", docenteSer.listAllDocente());
		model.addAttribute("cursos", cursoSer.listAllCurso());
		model.addAttribute("secciones", seccionSer.listAllSeccion());
		if(seccionSer.listAllSeccion().spliterator().getExactSizeIfKnown()==0){
			model.addAttribute("messagevacio", "NO HAY REGISTROS");
		}
		return "newSeccion";
	}
	}
	
	@RequestMapping(value="/deleteSeccion{cseccion}")
	public String deleteSeccion(@PathVariable("cseccion") int cseccion, Model model, RedirectAttributes ra)
	{
		try
		{
			seccionSer.deleteSeccion(cseccion);
			ra.addFlashAttribute("messageexito", "Seccion eliminada del sistema con exito");
			return "redirect:/nuevaseccion";
		}
		catch (Exception e)
		{
			model.addAttribute("messageerror", "ERROR: No se puede eliminar una sección que está incluida dentro de alguna MATRÍCULA u HORARIO.");
			model.addAttribute("docentes", docenteSer.listAllDocente());
			model.addAttribute("cursos", cursoSer.listAllCurso());
			model.addAttribute("seccion", new seccion());
			model.addAttribute("secciones", seccionSer.listAllSeccion());
			return "newSeccion";
		}
	}
	
	@RequestMapping(value="/editarSeccion{cseccion}",method=RequestMethod.GET)
	public String editarSeccion(@PathVariable("cseccion") int cseccion, Model model){
	    model.addAttribute("seccion", seccionSer.GetSeccion(cseccion));
	    model.addAttribute("docentes", docenteSer.listAllDocente());
		model.addAttribute("cursos", cursoSer.listAllCurso());
	    return "editarSeccion";
		
	}
}
