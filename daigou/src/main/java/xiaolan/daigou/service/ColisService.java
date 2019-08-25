package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.domain.dto.ArticleMapEnRoutesDTO;
import xiaolan.daigou.domain.dto.ArticleStockageDTO;
import xiaolan.daigou.domain.dto.ColisDTO;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Colis;

public interface ColisService extends AbstractService<Colis> {

	Colis createColis(Long idUser);
	
	List<Colis> getColisByStatus(int status, Long idUser);

	Colis envoyerColis(Colis colis);
	
	ColisDTO arriverColis(ArticleMapEnRoutesDTO articlesMapEnRoutesDTO);
	
	Article putArticleStockageInColis(ArticleStockageDTO articleStockageDTO, int idColis, int countArticleStockage);
}

