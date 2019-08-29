package xiaolan.daigou.dao;

import java.util.List;

import xiaolan.daigou.model.entity.Article;
import xiaolan.daigou.model.entity.Colis;
import xiaolan.daigou.model.enums.EnumStatusColis;

public interface ColisDao extends BaseDao<Colis>{
	List<Colis> getColisByStatus(int status, Long idUser);
	
	Colis getLastColis(Long idUser);
}
