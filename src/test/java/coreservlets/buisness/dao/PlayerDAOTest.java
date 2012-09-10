package coreservlets.buisness.dao;

import coreservlets.business.dao.BattleDAO;
import coreservlets.business.dao.PlayerBattleDAO;
import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Battle;
import coreservlets.business.model.Player;
import coreservlets.business.model.PlayerBattle;
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
    @Resource(name = "playerBattleDAO")
    private PlayerBattleDAO playerBattleDAO;

    @Test
    public void playerTestDAO() {
        LOGGER.debug("/////////////Inicio de TEST");
        
        Player player = new Player();
        player.setName("kyo");
        
        Battle battle = new Battle();
        
        PlayerBattle playerBattle = new PlayerBattle();
        playerBattle.setPlayer(player);
        playerBattle.setBattle(battle);
        
        playerDAO.persist(player);
        battleDAO.persist(battle);
        playerBattleDAO.persist(playerBattle);
        
        playerDAO.persist(player);
        List<Player> players = playerDAO.findAll();
        Assert.assertFalse(players.isEmpty());
        Assert.assertEquals(players.size(), 1);
        Assert.assertEquals(player, players.get(0));
    }
}
