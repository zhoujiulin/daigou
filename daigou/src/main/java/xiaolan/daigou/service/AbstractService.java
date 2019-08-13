package xiaolan.daigou.service;

import java.util.List;

public interface AbstractService<T> {
	
	T save(T entity);
	
	void delete(T entity);
	
	void deleteById(Long id);
	
	T findById(Long id);
	
	List<T> getAll(Long userId);
}
