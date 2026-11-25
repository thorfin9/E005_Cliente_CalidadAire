package com.ipartek.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class ContaminanteControlador {
	@GetMapping("/contaminantes")
	public String contaminantes(Model model) {
		model.addAttribute("pestana", "contaminantes");
		return "contaminantes";
	}
}