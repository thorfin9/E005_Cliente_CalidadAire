package com.ipartek.servicios;

import org.springframework.http.ResponseEntity;

public interface MedicionServicio {
	public ResponseEntity<?> obtenerUltimas();
}