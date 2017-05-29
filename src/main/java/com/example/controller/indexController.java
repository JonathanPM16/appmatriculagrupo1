package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.admin;
import com.example.service.adminService;


@Controller
public class indexController {

	@Autowired
	private adminService adminSer;
	
	@RequestMapping(value="/inicio")
	public String inicio()
	{
		return "index";
	}
	
	@RequestMapping(value="/")
	public String usuario()
	{
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String iniciarSesion(@RequestParam String usuario, @RequestParam String password, Model model,
			                    HttpSession session)
	{
	   admin admin = adminSer.autenticarAdmin(usuario, password);
	   if (usuario=="" || password=="") {
		   model.addAttribute("message", "ingresar los datos completos");
		   return "login";
	   }
	   else if (admin==null) {
		   model.addAttribute("message", "los datos ingresados no son correctos");
		   return "login";
	   }
	   session.setAttribute("usuariologeado", admin);
	   return "redirect:/inicio";
	}
}
