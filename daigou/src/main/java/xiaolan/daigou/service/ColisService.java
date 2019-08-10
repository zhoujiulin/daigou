package xiaolan.daigou.service;

import java.util.List;
import java.util.Map;

import xiaolan.daigou.domain.entity.Colis;

public interface ColisService {

	Colis createColis(Colis colis, Long idUser);
	
	List<Colis> getColisByStatus(int status, Long idUser);

	Colis envoyerColis(Colis colis);
	
	Colis arriverColis(Colis colis);
}

