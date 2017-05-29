package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entities.curso;
import com.example.service.cursoService;

@Controller
public class cursoController {
	
	@Autowired
	private cursoService cursoser;
	
	@RequestMapping(value="/nuevocurso")
	public String cursos(Model model)
	{
		model.addAttribute("curso",new curso());
		model.addAttribute("cursos", cursoser.listAllCurso());
		return "newCurso";
	}
	
	@RequestMapping(value="/curso/new", method=RequestMethod.POST)
	public String saveCurso(@Valid curso c, BindingResult result, Model model){
		
		try {
			if(result.hasErrors()){
				model.addAttribute("message", result.toString());
				model.addAttribute("cursos", cursoser.listAllCurso());
				return "newCurso";
			}
			cursoser.saveCurso(c);
			return "redirect:/nuevocurso";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("cursos", cursoser.listAllCurso());
			return "newCurso";
		}
	}
	
	@RequestMapping( value="/deletecurso{id}")
	public String deletecurso(@PathVariable int id, Model model)
	{
		try
		{
			cursoser.deleteCurso(id);
			return"redirect:/nuevocurso";
		}
		catch (Exception e)
		{
			model.addAttribute("message", "ERROR: No se puede eliminar un curso que está incluido dentro de alguna SECCIÓN.");
			model.addAttribute("curso",new curso());
			model.addAttribute("cursos", cursoser.listAllCurso());
			return "newCurso";
		}
	}
	
	@RequestMapping(value="/editarCurso{ccu}",method=RequestMethod.GET)
	public String editarAlumno(@PathVariable int ccu,Model model)
	{
		model.addAttribute("curso", cursoser.getporccurso(ccu));
		return "editarCurso";
	}

}
