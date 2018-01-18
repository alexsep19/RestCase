package dao.api;

import api.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractEntityDao<I extends Serializable, E extends AbstractEntity<I>> {


    @PersistenceContext(unitName = "jpaRolo")
    private EntityManager em;

    private Class<E> eClass;

    public AbstractEntityDao(Class<E> eClass) {
        this.eClass = eClass;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public E findByUnic(String unic) {
        throw new UnsupportedOperationException();
    }

    public E find(I id) {
        return em.find(eClass, id);
    }

    public E update(E entity) {
        return em.merge(entity);
    }

    public E save(E entity) {
        em.persist(entity);
        return entity;
    }

    public void remove(E entity) {
        em.remove(entity);
    }

    public List<E> list() {
        return list(null);
    }

    public List<E> list(Integer length) {
        return list(null, length);
    }

    public List<E> list(Integer offset, Integer length) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(eClass);
        Root<E> root = criteria.from(eClass);
        criteria.select(root);
        TypedQuery<E> query = em.createQuery(criteria);
        if (offset != null) query.setFirstResult(offset);
        if (length != null) query.setMaxResults(length);
        return query.getResultList();
    }

    public Long count() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<E> root = criteria.from(eClass);
        criteria.select(builder.count(root));
        return em.createQuery(criteria).getSingleResult();
    }
}

