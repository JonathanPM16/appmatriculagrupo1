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
		if(cursoser.listAllCurso().spliterator().getExactSizeIfKnown()==0){
			model.addAttribute("messagevacio", "NO HAY REGISTROS");
		}
		return "newCurso";
	}
	
	@RequestMapping(value="/curso/new", method=RequestMethod.POST)
	public String saveCurso(@Valid curso c, BindingResult result, Model model, RedirectAttributes ra){
		
		try {
			if(result.hasErrors()){
				//model.addAttribute("messageerror", result.toString());
				model.addAttribute("cursos", cursoser.listAllCurso());
				return "newCurso";
			}else if(cursoser.validarcursorepetido(c)!=null){
				model.addAttribute("messageerror", "El curso que intenta registrar ya existe en el sistema");
				model.addAttribute("cursos", cursoser.listAllCurso());
				return "newCurso";
			}else if(c.getNombrecurso().substring(0,1).equals(" ") || c.getNombrecurso().substring(0,1).equals(" ") ||
					 c.getDescripcion().substring(0,1).equals(" ")){
				model.addAttribute("cursos", cursoser.listAllCurso());
				model.addAttribute("messageespacios", "No use espacios para iniciar los campos");
				if(cursoser.listAllCurso().spliterator().getExactSizeIfKnown()==0){
					model.addAttribute("messagevacio", "NO HAY REGISTROS");
				}
				return "newCurso";
			}
			cursoser.saveCurso(c);
			ra.addFlashAttribute("messageregistro", "Curso registrado con exito");	
			return "redirect:/nuevocurso";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());
			model.addAttribute("cursos", cursoser.listAllCurso());
			return "newCurso";
		}
	}
	
	@RequestMapping(value="/curso/update", method=RequestMethod.POST)
	public String updateCurso(@Valid curso c, BindingResult result, Model model, RedirectAttributes ra){
		
		try {
			if(result.hasErrors()){
				//model.addAttribute("messageerror", result.toString());
				model.addAttribute("cursos", cursoser.listAllCurso());
				return "editarCurso";
			}else if(cursoser.validarcursorepetido(c)!=null){
				model.addAttribute("messageerror", "No se detectaron cambios");
				model.addAttribute("cursos", cursoser.listAllCurso());
				return "editarCurso";
			}else if(c.getNombrecurso().substring(0,1).equals(" ") || c.getNombrecurso().substring(0,1).equals(" ") ||
					 c.getDescripcion().substring(0,1).equals(" ")){
				model.addAttribute("messageespacios", "No use espacios para iniciar los campos");
				if(cursoser.listAllCurso().spliterator().getExactSizeIfKnown()==0){
					model.addAttribute("messagevacio", "NO HAY REGISTROS");
				}
				return "editarCurso";
			}
			cursoser.saveCurso(c);
			ra.addFlashAttribute("messageregistro", "Curso actualizado con exito");	
			return "redirect:/nuevocurso";
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());		
			model.addAttribute("cursos", cursoser.listAllCurso());
			return "editarCurso";
		}
	}
	
	@RequestMapping( value="/deletecurso{id}")
	public String deletecurso(@PathVariable int id, Model model, RedirectAttributes ra)
	{
		try
		{
			cursoser.deleteCurso(id);
			ra.addFlashAttribute("messageexito", "Curso eliminado del sistema con exito");
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
