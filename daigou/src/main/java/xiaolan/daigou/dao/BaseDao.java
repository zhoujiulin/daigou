package xiaolan.daigou.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface BaseDao<T> {

	void create(T entity);
	
	T save(T entity);
	
	void delete(T entity);
	
	void deleteById(Long id);
	
	T findById(Long id);
	
	List<T> getAll();
}
