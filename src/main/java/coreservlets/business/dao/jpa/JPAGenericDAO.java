package coreservlets.business.dao.jpa;

import coreservlets.business.dao.GenericDAO;
import coreservlets.business.model.IdEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.transaction.annotation.Transactional;

public class JPAGenericDAO<T extends IdEntity> implements GenericDAO<T> {

    private Class clazz = null;
    @PersistenceContext
    private EntityManager entityManager;

    public JPAGenericDAO(Class clazz) {
        super();
        this.clazz = clazz;
    }

    @Transactional
    public T persist(T object) {
        T result = null;
        if (object != null) {
            final T newObject = (T) this.entityManager.merge(object);
            result = newObject;
        }
        return result;
    }

    @Transactional
    public void delete(T object) {
        if (object != null) {
            object = this.entityManager.merge(object);
            this.entityManager.remove(object);
        }
    }

    @Transactional(readOnly = true)
    public T find(long id) {
        try {
            T result = (T) this.entityManager.find(clazz, id);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        CriteriaQuery cq = this.entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(clazz));
        return this.entityManager.createQuery(cq).getResultList();

    }

    public Class getEntityClass() {
        return this.clazz;
    }
}
