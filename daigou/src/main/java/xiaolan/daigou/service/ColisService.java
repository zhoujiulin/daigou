package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.model.dto.ArticleMapEnRoutesDTO;
import xiaolan.daigou.model.dto.ArticleStockageDTO;
import xiaolan.daigou.model.dto.ColisDTO;
import xiaolan.daigou.model.entity.Article;
import xiaolan.daigou.model.entity.Colis;

public interface ColisService extends AbstractService<Colis> {

	Colis createColis(Long idUser);
	
	List<Colis> getColisByStatus(int status, Long idUser);

	Colis envoyerColis(Colis colis);
	
	ColisDTO arriverColis(ArticleMapEnRoutesDTO articlesMapEnRoutesDTO);
	
	Article putArticleStockageInColis(ArticleStockageDTO articleStockageDTO, int idColis, int countArticleStockage);
}

