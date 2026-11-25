package com.ipartek.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ipartek.modelo.TablaMediciones;


@Service
public class MedicionServicioImp implements MedicionServicio {

	@Value("${url.api.mediciones}")
	private String URL;
	private RestClient restClient = RestClient.create();

	@Override
	public ResponseEntity<?> obtenerUltimas() {
		return restClient.get().uri(URL).retrieve().toEntity(TablaMediciones.class);
	}
}