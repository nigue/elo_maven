package coreservlets.buisness.dao;

import coreservlets.business.dao.BattleDAO;
import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Battle;
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
    @Resource(name = "battleDAO")
    private BattleDAO battleDAO;

    @Test
    public void playerTestDAO() {
        LOGGER.debug("/////////////Inicio de TEST");

        Player player1 = new Player();
        Player player2 = new Player();
        player1.setId(new Long(1));
        player1.setName("kyo");
        player1.setElo(1500);
        player1.setNumGames(0);
        playerDAO.persist(player1);
        player2.setId(new Long(2));
        player2.setName("leona");
        player2.setElo(1500);
        player2.setNumGames(0);
        playerDAO.persist(player2);

        Battle battle = new Battle();
        battle.setId(new Long(1));
        battle.setWinner(player1);
        battle.setLosser(player2);
        this.doElo(player1, player2);
        battleDAO.persist(battle);

        List<Player> players = playerDAO.findAll();
        Assert.assertFalse(players.isEmpty());
//        Assert.assertEquals(players.size(), 1);
//        Assert.assertEquals(player1, players.get(0));
    }

    public void doElo(Player winner, Player losser) {
        int elo1 = getMathElo(winner, losser);
        int elo2 = getMathElo(losser, winner, Math.abs(1 - 1));
        winner.setElo(elo1);
        losser.setElo(elo2);
        winner.setNumGames(winner.getNumGames() + 1);
        losser.setNumGames(losser.getNumGames() + 1);
        playerDAO.persist(winner);
        playerDAO.persist(losser);
    }

    public int getMathElo(Player winner, Player losser) {

        double expected = 1 / (1 + Math.pow(10, ((losser.getElo() - winner.getElo()) / 400)));
        double K = getK(winner);
        double newElo = winner.getElo() + Math.round(K * ((1 - 0.25) + 0.25 * ((10 - 0) / 10)) * (1 - expected));
        return Math.round((float) newElo);

    }

    public int getMathElo(Player winner, Player losser, int resultado) {

        double expected = 1 / (1 + Math.pow(10, ((losser.getElo() - winner.getElo()) / 400)));
        double K = getK(winner);
        double newElo = winner.getElo() + Math.round(K * ((1 - 0.25) + 0.25 * ((10 - 0) / 10)) * (resultado - expected));
        return Math.round((float) newElo);

    }

    public static double getK(Player player) {
        if (player.getElo() > 2000 || player.getNumGames() > 40) {
            return 10;
        }
        if (player.getNumGames() > 20) {
            return 15;
        }
        return 25;
    }
}
