package coreservlets.business.dao.jpa;

import coreservlets.business.dao.BattleDAO;
import coreservlets.business.model.Battle;
import org.springframework.stereotype.Service;

@Service(value = "battleDAO")
public class JPABattleDAO extends JPAGenericDAO<Battle> implements BattleDAO {
    
        public JPABattleDAO() {
        super(Battle.class);
    }
    
}
