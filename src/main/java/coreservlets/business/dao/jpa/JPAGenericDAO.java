package coreservlets.business.dao.jpa;

import coreservlets.business.dao.GenericDAO;
import coreservlets.business.model.IdEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    @Override
    public T persist(T object) {
        T result = null;
        if (object != null) {
            final T newObject = (T) this.entityManager.merge(object);
            result = newObject;
        }
        return result;
    }

    @Transactional
    @Override
    public void delete(T object) {
        if (object != null) {
            object = this.entityManager.merge(object);
            this.entityManager.remove(object);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public T find(long id) {
        try {
            T result = (T) this.entityManager.find(clazz, id);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        CriteriaQuery cq = this.entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(clazz));
        return this.entityManager.createQuery(cq).getResultList();

    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByField(String field, String data) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cQuery = builder.createQuery(clazz);
        Root<T> from = cQuery.from(clazz);
        CriteriaQuery<T> select = cQuery.select(from);
        Expression<String> name = from.get(field);
        Predicate eq = builder.equal(name, data);
        select.where(eq);
        TypedQuery typedQuery = this.entityManager.createQuery(select);
        return typedQuery.getResultList();
    }

    @Override
    public Class getEntityClass() {
        return this.clazz;
    }
}
