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

import com.example.entities.horario;
import com.example.entities.matricula;
import com.example.entities.seccion;
import com.example.service.alumnoService;
import com.example.service.horarioService;
import com.example.service.matriculaService;
import com.example.service.seccionService;

@Controller
public class matriculaController {

	@Autowired
	private matriculaService matriculaSer;
	
	@Autowired
	private alumnoService alumnoSer;
	
	@Autowired
	private seccionService seccionSer;
	
	@Autowired
	private horarioService horarioSer;
	
	private boolean hayCruce(matricula m, Iterable<horario> listh, Iterable<matricula> listmat){
		for (matricula mat : listmat) 
		{
			seccion s1 = mat.getSeccion();
			Iterable<horario> listh1 = horarioSer.ListHorarioXSeccion(s1);
			for (horario h1 : listh1) 
			{
				for (horario h2 : listh)
				{
					if(h1.getDia().equals(h2.getDia()))
					{
						if(h1.getHorainicio()>=h2.getHorafin() || h2.getHorainicio()>=h1.getHorafin())
						{
							
						}
						else
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@RequestMapping(value="/nuevamatricula")
	public String matricula(Model model){
		model.addAttribute("alumnos", alumnoSer.listAllAlumno());
		model.addAttribute("secciones", seccionSer.listarSeccionMenor(30));
		model.addAttribute("seccionesllenas", seccionSer.listarSeccionIgual(30));
		model.addAttribute("matricula", new matricula());
		model.addAttribute("matriculas", matriculaSer.ListAllMatricula());
		if(matriculaSer.ListAllMatricula().spliterator().getExactSizeIfKnown()==0){
			model.addAttribute("messagevacio", "NO HAY REGISTROS");
		}
		return "newMatricula";
	}
	
	@RequestMapping(value="/matricula/new", method=RequestMethod.POST)
	public String saveMatricula(@Valid matricula m, BindingResult result, Model model, RedirectAttributes ra){
	   try {
		if (result.hasErrors()) {
			model.addAttribute("message", result.toString());
			model.addAttribute("alumnos", alumnoSer.listAllAlumno());
			model.addAttribute("secciones", seccionSer.listarSeccionMenor(30));
			model.addAttribute("seccionesllenas", seccionSer.listarSeccionIgual(30));
			model.addAttribute("matriculas", matriculaSer.ListAllMatricula());
			if(matriculaSer.ListAllMatricula().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newMatricula";
		}else if(m.getAlumno()==null || m.getSeccion()==null){
			model.addAttribute("message", "por favor seleccione todos los campos");
			model.addAttribute("alumnos", alumnoSer.listAllAlumno());
			model.addAttribute("secciones", seccionSer.listarSeccionMenor(30));
			model.addAttribute("seccionesllenas", seccionSer.listarSeccionIgual(30));
			model.addAttribute("matriculas", matriculaSer.ListAllMatricula());
			if(matriculaSer.ListAllMatricula().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newMatricula";
		}
		seccion s=seccionSer.GetSeccion(m.getSeccion().getCseccion());
		Iterable<horario> listh = horarioSer.ListHorarioXSeccion(s);
		Iterable<matricula> listmat = matriculaSer.listarXAlumno(m.getAlumno());
		if(hayCruce(m, listh, listmat))
		{
			model.addAttribute("message", "ERROR: El ALUMNO presenta un cruce con el HORARIO de la SECCIÓN seleccionada.");
			model.addAttribute("alumnos", alumnoSer.listAllAlumno());
			model.addAttribute("secciones", seccionSer.listarSeccionMenor(30));
			model.addAttribute("seccionesllenas", seccionSer.listarSeccionIgual(30));
			model.addAttribute("matriculas", matriculaSer.ListAllMatricula());
			if(matriculaSer.ListAllMatricula().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newMatricula";
		}
		matriculaSer.saveMatricula(m);
		s.setNummatriculados(matriculaSer.listarXSeccion(s).size());
		seccionSer.saveSeccion(s);	
		ra.addFlashAttribute("messageregistro", "Matricula registrada con exito");	
		return "redirect:/nuevamatricula";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", e.getMessage());
			model.addAttribute("alumnos", alumnoSer.listAllAlumno());
			model.addAttribute("secciones", seccionSer.listarSeccionMenor(30));
			model.addAttribute("seccionesllenas", seccionSer.listarSeccionIgual(30));
			model.addAttribute("matriculas", matriculaSer.ListAllMatricula());
			if(matriculaSer.ListAllMatricula().spliterator().getExactSizeIfKnown()==0){
				model.addAttribute("messagevacio", "NO HAY REGISTROS");
			}
			return "newMatricula";
		}
	}
	
	@RequestMapping(value="/deleteMatricula{cmatricula}")
	public String deleteMatricula(@PathVariable("cmatricula") int cmatricula, Model model, RedirectAttributes ra){
		matricula m=matriculaSer.getbyCmatricula(cmatricula);
		seccion s=seccionSer.GetSeccion(m.getSeccion().getCseccion());
		matriculaSer.deleteMatricula(cmatricula);
		s.setNummatriculados(matriculaSer.listarXSeccion(s).size());
		seccionSer.saveSeccion(s);
		ra.addFlashAttribute("messageexito", "Matricula eliminada del sistema con exito");
		return "redirect:/nuevamatricula";
	}
	
	@RequestMapping(value="/editarMatricula{cmatricula}",method=RequestMethod.GET)
	public String editarMatricula(@PathVariable("cmatricula") int cmatricula, Model model){
		model.addAttribute("matricula", matriculaSer.getbyCmatricula(cmatricula));
	    model.addAttribute("alumnos", alumnoSer.listAllAlumno());
	    model.addAttribute("secciones", seccionSer.listarSeccionMenor(30));
		model.addAttribute("seccionesllenas", seccionSer.listarSeccionIgual(30));
		matricula m=matriculaSer.getbyCmatricula(cmatricula);
		seccion s=seccionSer.GetSeccion(m.getSeccion().getCseccion());
		s.setNummatriculados(matriculaSer.listarXSeccion(s).size());
		seccionSer.saveSeccion(s);
	    return "editarMatricula";
	    
		
	}
}
