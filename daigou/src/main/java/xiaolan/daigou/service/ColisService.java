package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.domain.entity.Colis;

public interface ColisService extends AbstractService<Colis> {

	Colis createColis(Colis colis, Long idUser);
	
	List<Colis> getColisByStatus(int status, Long idUser);

	Colis envoyerColis(Colis colis);
	
	Colis arriverColis(Colis colis);
}

