package coreservlets.business.dao.jpa;

import coreservlets.business.dao.PlayerBattleDAO;
import coreservlets.business.model.PlayerBattle;
import org.springframework.stereotype.Service;

@Service(value = "playerBattleDAO")
public class JPAPlayerBattleDAO extends JPAGenericDAO<PlayerBattle> implements PlayerBattleDAO {

    public JPAPlayerBattleDAO() {
        super(PlayerBattle.class);
    }
}
