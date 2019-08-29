package xiaolan.daigou.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.model.exception.DaigouException;
import xiaolan.daigou.service.AbstractService;

public abstract class AbstractServiceImpl<T> implements AbstractService<T>{

    private Class<T> clazz;
	
    public AbstractServiceImpl(final Class<T> clazz) {
        this.clazz = clazz;
    }

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public T save(T entity) {
		return this.getDao().save(entity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public void delete(T entity) {
		this.getDao().delete(entity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public void deleteById(Long id) {
		this.getDao().deleteById(id);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public T findById(Long id) {
        if (id == null) {
            //throw new MetroTechnicalException("ID ne peut être null");
        }

        T entity = this.getDao().findById(id);
        return entity;
    }

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public List<T> getAll(Long userId) {

		return this.getDao().getAll(userId);
	}
	
    public abstract BaseDao<T> getDao();
}
