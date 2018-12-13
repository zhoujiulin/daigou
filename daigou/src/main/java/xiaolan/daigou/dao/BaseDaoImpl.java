package xiaolan.daigou.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDaoImpl<T> implements BaseDao<T>{

    /**
     * from String
     */
    protected static final String FROM = " FROM ";
    protected static final String VIDE = "";
    protected static final String SPACE = " ";
    protected static final String AND = " and ";
    protected static final String WHERE = " where ";
	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;
	
	protected Class<T> clazz = null;
	
	public BaseDaoImpl(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void create(T entity) {
		if(entity != null) {
			em.persist(entity);
		}
	}
	
	@Override
	public T save(T entity) {
		T res = null;
		if(entity != null) {
			res = em.merge(entity);
		}
		return res;
	}

	@Override
	public void delete(T entity) {
		em.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		T t = findById(id);
		delete(t);
	}

	@Override
	public T findById(Long id) {
		T result = null;
		if(id != null){
			result = em.find(this.clazz, id);
		}
		
		return result;
	}

	@Override
	public List<T> getAll() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(FROM);
        queryBuilder.append(this.clazz.getSimpleName());

        TypedQuery<T> contentQuery = em.createQuery(queryBuilder.toString(), this.clazz);

        return contentQuery.getResultList();
	}

}
