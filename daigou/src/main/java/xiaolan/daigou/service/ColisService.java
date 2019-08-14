package xiaolan.daigou.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Colis;

public interface ColisService extends AbstractService<Colis> {

	Colis createColis(Long idUser);
	
	List<Colis> getColisByStatus(int status, Long idUser);

	Colis envoyerColis(Colis colis);
	
	Colis arriverColis(Colis colis);
	
	Article putArticleStockageInColis(ArticleStockage articleStockage, int idColis, int countArticleStockage);
}

