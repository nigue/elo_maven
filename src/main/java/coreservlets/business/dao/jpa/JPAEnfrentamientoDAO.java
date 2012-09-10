package coreservlets.business.dao.jpa;

import coreservlets.business.dao.EnfrentamientoDAO;
import coreservlets.business.model.Enfrentamiento;
import org.springframework.stereotype.Service;

@Service(value = "enfrentamientoDAO")
public class JPAEnfrentamientoDAO extends JPAGenericDAO<Enfrentamiento> implements EnfrentamientoDAO {

    public JPAEnfrentamientoDAO() {
        super(Enfrentamiento.class);
    }
}
