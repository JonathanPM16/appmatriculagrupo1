package com.example.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.horario;
import com.example.entities.seccion;
import com.example.service.horarioService;
import com.example.service.matriculaService;
import com.example.service.salonService;
import com.example.service.seccionService;

@Controller
public class horarioController {

	@Autowired
	private horarioService horarioSer;
	
	@Autowired
	private seccionService seccionSer;
	
	@Autowired
	private salonService salonSer;
	
	@Autowired
	private matriculaService matriculaSer;
	
	@RequestMapping(value="/nuevohorario")
	public String horarios(Model model){
		model.addAttribute("horarios", horarioSer.ListAllHorario());
		model.addAttribute("secciones", seccionSer.listAllSeccion());
		model.addAttribute("salones", salonSer.listAllSalon());
		model.addAttribute("horario", new horario());
		return "newHorario";
	}
	
	@RequestMapping(value="/horario/new", method=RequestMethod.POST)
	public String saveHorario(@Valid horario h, Model model, BindingResult result, @RequestParam int horainicio, @RequestParam int horafin){
		try {
			if (result.hasErrors()) {
				model.addAttribute("message", result.toString());
				model.addAttribute("secciones", seccionSer.listAllSeccion());
				model.addAttribute("salones", salonSer.listAllSalon());
				model.addAttribute("horarios", horarioSer.ListAllHorario());
				return "newHorario";
			}
			else if(horainicio>=horafin)
			{
				model.addAttribute("messagehorasnovalidas", "ERROR: La hora final es igual o menor que la inicial.");
				model.addAttribute("secciones", seccionSer.listAllSeccion());
				model.addAttribute("salones", salonSer.listAllSalon());
				model.addAttribute("horarios", horarioSer.ListAllHorario());
				return "newHorario";
			}
			else
			{
				Iterable<horario> listhor = horarioSer.ListHorarioXDia(h.getDia());
				for (horario hor : listhor) {
					if(hor.getSalon().getCsalon()==h.getSalon().getCsalon())
					{
						if(hor.getHorainicio()>=h.getHorafin() || h.getHorainicio()>=hor.getHorafin())
						{
							
						}
						else
						{
							model.addAttribute("messagehorasnovalidas", "ERROR: En este día este SALÓN ya es utilizado durante algún momento entre las horas seleccionadas.");
							model.addAttribute("secciones", seccionSer.listAllSeccion());
							model.addAttribute("salones", salonSer.listAllSalon());
							model.addAttribute("horarios", horarioSer.ListAllHorario());
							return "newHorario";
						}
					}
					if(seccionSer.GetSeccion(h.getSeccion().getCseccion()).getDocente().equals(hor.getSeccion().getDocente()))					
					{
						if(hor.getHorainicio()>=h.getHorafin() || h.getHorainicio()>=hor.getHorafin())
						{
							
						}
						else
						{
							model.addAttribute("messagehorasnovalidas", "ERROR: En este día este PROFESOR ya enseña durante algún momento entre las horas seleccionadas.");
							model.addAttribute("secciones", seccionSer.listAllSeccion());
							model.addAttribute("salones", salonSer.listAllSalon());
							model.addAttribute("horarios", horarioSer.ListAllHorario());
							return "newHorario";
						}
					}
				}	
			}
			horarioSer.saveHorario(h);
			return "redirect:/nuevohorario";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", e.getMessage());
			model.addAttribute("secciones", seccionSer.listAllSeccion());
			model.addAttribute("salones", salonSer.listAllSalon());
			model.addAttribute("horarios", horarioSer.ListAllHorario());
			return "newHorario";
		}
	}
	
	@RequestMapping(value="/deleteHorario{chorario}")
	public String deleteHorario(@PathVariable("chorario") int chorario, Model model){
		seccion s = horarioSer.getHorario(chorario).getSeccion();
		if(matriculaSer.listarXSeccion(s).size()!=0)
		{
			model.addAttribute("messagehorasnovalidas", "ERROR: No se puede eliminar un HORARIO de una SECCIÓN en la cual exista una MATRÍCULA registrada.");
			model.addAttribute("secciones", seccionSer.listAllSeccion());
			model.addAttribute("salones", salonSer.listAllSalon());
			model.addAttribute("horarios", horarioSer.ListAllHorario());
			model.addAttribute("horario", new horario());
			return "newHorario";
		}
		horarioSer.deleteHorario(chorario);
		return "redirect:/nuevohorario";
	}
	
	@RequestMapping(value="/editarHorario{chorario}")
	public String editarHorario(@PathVariable("chorario") int chorario, Model model){
		model.addAttribute("horario", horarioSer.getHorario(chorario));
		model.addAttribute("secciones", seccionSer.listAllSeccion());
		model.addAttribute("salones", salonSer.listAllSalon());
		return "editarHorario";
	}
}
