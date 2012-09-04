package coreservlets.buisness.dao;

import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Player;
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

    @Test
    public void playerTestDAO() {
        LOGGER.debug("Inicio de TEST");
        Player player = new Player();
        player.setName("alfredin");
        playerDAO.persist(player);
        List<Player> players = playerDAO.findAll();
        Assert.assertFalse(players.isEmpty());
        Assert.assertEquals(players.size(), 1);
        Assert.assertEquals(player, players.get(0));
    }
}
