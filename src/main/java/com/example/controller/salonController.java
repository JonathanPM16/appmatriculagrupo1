package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entities.salon;
import com.example.service.salonService;

@Controller
public class salonController 
{
	@Autowired
	private salonService salonSer;
	
	@RequestMapping(value="/nuevosalon")
	public String salones(Model model)
	{
		model.addAttribute("salon", new salon());
		model.addAttribute("salones", salonSer.listAllSalon());
		return "newSalon";
	}
	
	@RequestMapping(value="/salon/new", method=RequestMethod.POST)
	public String saveSalon(@Valid salon s, BindingResult result, Model model)
	{
		try 
		{
			if(result.hasErrors())
			{
				model.addAttribute("message", result.toString());
				model.addAttribute("salones", salonSer.listAllSalon());
				return "newSalon";
			}
			salonSer.saveSalon(s);
			return "redirect:/nuevosalon";
		} 
		catch (Exception e) 
		{
			model.addAttribute("message", e.getMessage());
			model.addAttribute("salones", salonSer.listAllSalon());
			return "newSalon";
		}
		
	}
	
	@RequestMapping(value="/deletesalon{id}")
	public String deleteSalon(@PathVariable int id, Model model)
	{
		try
		{
			salonSer.deleteSalon(id);
			return "redirect:/nuevosalon";
		}
		catch (Exception e)
		{
			model.addAttribute("message", "ERROR: No se puede eliminar un salón que está incluido dentro de algún HORARIO.");
			model.addAttribute("salon", new salon());
			model.addAttribute("salones", salonSer.listAllSalon());
			return "newSalon";
		}
	}
	
	@RequestMapping(value="/editarSalon{csalon}",method=RequestMethod.GET)
	public String editarAlumno(@PathVariable int csalon,Model model)
	{
		model.addAttribute("salon", salonSer.getByCSalon(csalon));
		return "editarSalon";
	}
}