package xiaolan.daigou.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.BaseDao;

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
    protected static final String AS = " as ";
    protected static final String EQAL = " = ";
	
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

	@Override
	public List<T> getAll(Long userId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(FROM);
        queryBuilder.append(this.clazz.getSimpleName());
        queryBuilder.append(AS);
        queryBuilder.append("c");
        queryBuilder.append(WHERE);
        queryBuilder.append("c.utilisateur.idUser");
        queryBuilder.append(EQAL);
        queryBuilder.append("?");

//        TypedQuery<T> query = em.createQuery(queryBuilder.toString(), this.clazz);
//        query.setParameter("userId", useId);
        Query query = em.createQuery(queryBuilder.toString(), this.clazz);
        query.setParameter(0, userId);

        return query.getResultList();
	}
}
