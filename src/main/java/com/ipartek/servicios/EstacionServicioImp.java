package com.ipartek.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.ipartek.modelo.Estacion;

@Service
public class EstacionServicioImp implements EstacionServicio {

	@Value("${url.api.estaciones}")
	private String URL;
	private RestClient restClient = RestClient.create();

	@Override
	public ResponseEntity<?> obtenerTodas() {
		return restClient.get().uri(URL).retrieve().toEntity(Estacion[].class);
	}
}