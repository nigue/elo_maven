package coreservlets.web.utils.data;

import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Player;
import coreservlets.web.bean.AddBackingBean;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataUtils {

    private static final Logger LOGGER = Logger.getLogger(AddBackingBean.class);

    public List<Player> getAllPlayers() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
                    "classpath:dataSourceContext.xml",
                    "classpath:modelContext.xml"});
        PlayerDAO playerDAO = (PlayerDAO) beanFactory.getBean("playerDAO");
        return playerDAO.findAll();
    }

    public List<Player> getPlayerByName(String name) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
                    "classpath:dataSourceContext.xml",
                    "classpath:modelContext.xml"});
        PlayerDAO playerDAO = (PlayerDAO) beanFactory.getBean("playerDAO");
        return playerDAO.findByField("name", name);
    }

    public void doElo(Player winner, Player losser) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(new String[]{
                    "classpath:dataSourceContext.xml",
                    "classpath:modelContext.xml"});
        PlayerDAO playerDAO = (PlayerDAO) beanFactory.getBean("playerDAO");
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
