package com.ipartek.controlador;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ipartek.modelo.TablaMediciones;
import com.ipartek.servicios.MedicionServicio;

@Controller
public class MedicionControlador {

	@Autowired
	private MedicionServicio medicionServ;

	@GetMapping("/mediciones")
	public String mediciones(Model model) {
		try {
			TablaMediciones tabla = (TablaMediciones) medicionServ.obtenerUltimas().getBody();
			if (tabla == null) {
				tabla = new TablaMediciones(new ArrayList<>(), new ArrayList<>());
			}
			model.addAttribute("tabla", tabla);
		} catch (Exception e) {
			model.addAttribute("tabla", new TablaMediciones(new ArrayList<>(), new ArrayList<>()));
			model.addAttribute("f_mensaje",
					"No se han podido cargar las mediciones. ¿Está arrancado el REST (puerto 9091)?");
		}
		model.addAttribute("pestana", "mediciones");
		return "mediciones";
	}
}