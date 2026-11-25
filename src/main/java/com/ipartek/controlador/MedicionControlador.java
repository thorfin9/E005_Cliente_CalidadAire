package com.ipartek.controlador;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ipartek.modelo.TablaMediciones;
import com.ipartek.servicios.MedicionServicio;

import java.util.Map;

@Controller
public class MedicionControlador {

	@Autowired
	private MedicionServicio medicionServ;
	
	// límite de cada contaminante (mismos valores que contaminantes.html)
	private static final Map<String, Double> LIMITES = Map.of(
			"SO2", 350.0,
			"NO2", 20.0,
			"O3", 180.0,
			"CO", 10.0,
			"PM10", 50.0,
			"PM2,5", 25.0);

	
	public boolean superaLimite(String contaminante, String valor) {
		if (valor == null) {
			return false;
		}
		Double limite = LIMITES.get(contaminante.toUpperCase());
		if (limite == null) {
			return false;
		}
		try {
			String numero = valor.split(" ")[0].replace(",", ".");
			return Double.parseDouble(numero) > limite;
		} catch (NumberFormatException e) {
			return false;
		}
	}	

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