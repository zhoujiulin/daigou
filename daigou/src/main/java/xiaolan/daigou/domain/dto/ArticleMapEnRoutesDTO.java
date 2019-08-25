package xiaolan.daigou.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ArticleMapEnRoutesDTO {
	ColisDTO colis;
	List<ArticleMapEnRouteDTO> articleMapEnRoutes = new ArrayList<ArticleMapEnRouteDTO>();
	
	public ColisDTO getColis() {
		return colis;
	}
	public void setColis(ColisDTO colis) {
		this.colis = colis;
	}
	public List<ArticleMapEnRouteDTO> getArticleMapEnRoutes() {
		return articleMapEnRoutes;
	}
	public void setArticleMapEnRoutes(List<ArticleMapEnRouteDTO> articleMapEnRoutes) {
		this.articleMapEnRoutes = articleMapEnRoutes;
	}
}
