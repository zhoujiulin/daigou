package xiaolan.daigou.dao;

import java.util.List;

import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Colis;

public interface ColisDao extends BaseDao<Colis>{
	List<Colis> getColisByStatus(int status, Long idUser);
}