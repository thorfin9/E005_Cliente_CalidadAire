package com.ipartek.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipartek.modelo.Estacion;
import com.ipartek.servicios.EstacionServicio;

// PESTAÑA 1: mapa
@Controller
public class MapaControlador {

	@Autowired
	private EstacionServicio estacionServ;

	@GetMapping("/mapa")
	public String mapa(Model model) {
		try {
			Estacion[] estaciones = (Estacion[]) estacionServ.obtenerTodas().getBody();
			model.addAttribute("estaciones", estaciones == null ? new Estacion[0] : estaciones);
		} catch (Exception e) {
			model.addAttribute("estaciones", new Estacion[0]);
			model.addAttribute("f_mensaje", "No se han podido cargar las estaciones. ¿Está arrancado el REST (puerto 9091)?");
		}
		model.addAttribute("pestana", "mapa");
		return "mapa";
	}
}