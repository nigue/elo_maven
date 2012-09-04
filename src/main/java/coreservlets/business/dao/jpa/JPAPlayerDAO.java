package coreservlets.business.dao.jpa;

import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Player;
import org.springframework.stereotype.Service;

@Service(value = "playerDAO")
public class JPAPlayerDAO extends JPAGenericDAO<Player> implements PlayerDAO {

    public JPAPlayerDAO() {
        super(Player.class);
    }
}
