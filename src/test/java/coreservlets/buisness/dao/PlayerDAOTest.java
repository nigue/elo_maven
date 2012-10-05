package coreservlets.buisness.dao;

import coreservlets.business.dao.BattleDAO;
import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Battle;
import coreservlets.business.model.Player;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:modelContext.xml"})
public class PlayerDAOTest {

    private static final Logger LOGGER = Logger.getLogger(PlayerDAOTest.class);
    
    @Resource(name = "playerDAO")
    private PlayerDAO playerDAO;
    @Resource(name = "battleDAO")
    private BattleDAO battleDAO;

    @Test
    public void playerTestDAO() {
        LOGGER.debug("/////////////Inicio de TEST");
        
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setId(new Long(1));
        player1.setName("kyo");
        playerDAO.persist(player1);
        player2.setId(new Long(2));
        player2.setName("leona");
        playerDAO.persist(player2);
        
        Battle battle = new Battle();
        battle.setId(new Long(1));
        battle.setWinner(player1);
        battle.setLosser(player2);
        battleDAO.persist(battle);
        
        List<Player> players = playerDAO.findAll();
        Assert.assertFalse(players.isEmpty());
//        Assert.assertEquals(players.size(), 1);
//        Assert.assertEquals(player1, players.get(0));
    }
}
