// 1) leemos las estaciones que Thymeleaf ha dejado en el HTML
const estaciones = Array.from(document.querySelectorAll('#datosEstaciones li')).map(function (li) {
	return {
		nombre: li.dataset.nombre,
		latitud: parseFloat(li.dataset.lat),
		longitud: parseFloat(li.dataset.lon)
	};
});

// 2) mapa centrado en Bizkaia
const mapa = L.map('mapa').setView([43.28, -2.9], 10);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
	maxZoom: 19,
	attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(mapa);


const puntos = [];

estaciones.forEach(function (e) {
	if (isNaN(e.latitud) || isNaN(e.longitud)) {
		return;
	}
	const pin = L.marker([e.latitud, e.longitud]).addTo(mapa);

	pin.bindTooltip(e.nombre, { direction: 'top', offset: [0, -30] });
	const negrita = document.createElement('b');
	negrita.textContent = e.nombre;
	pin.bindPopup(negrita);

	puntos.push([e.latitud, e.longitud]);
});


if (puntos.length > 0) {
	mapa.fitBounds(puntos, { padding: [40, 40] });
}