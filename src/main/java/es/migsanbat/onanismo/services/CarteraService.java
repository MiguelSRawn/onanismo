package es.migsanbat.onanismo.services;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.repositories.CarteraRepository;

public class CarteraService {
	private static CarteraService instancia;
	
	public static CarteraService get() {
		if(instancia==null) {
			instancia = new CarteraService();
		}
		return instancia;
	}
	public void addBalance(float num) {
		
	}

	public Cartera save(Cartera cartera) {
		try {
			CarteraRepository.save(cartera);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartera;
	}
}
