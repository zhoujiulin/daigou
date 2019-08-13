package xiaolan.daigou.service.impl;

import java.util.List;

import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.service.AbstractService;

public abstract class AbstractServiceImpl<T> implements AbstractService<T>{

    private Class<T> clazz;
	
    public AbstractServiceImpl(final Class<T> clazz) {
        this.clazz = clazz;
    }

	@Override
	public T save(T entity) {
		return this.getDao().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getDao().delete(entity);
	}

	@Override
	public void deleteById(Long id) {
		this.getDao().deleteById(id);
	}

	@Override
	public T findById(Long id) {
        if (id == null) {
            //throw new MetroTechnicalException("ID ne peut être null");
        }

        T entity = this.getDao().findById(id);
        return entity;
    }

	@Override
	public List<T> getAll(Long userId) {

		return this.getDao().getAll(userId);
	}
	
    public abstract BaseDao<T> getDao();
}
