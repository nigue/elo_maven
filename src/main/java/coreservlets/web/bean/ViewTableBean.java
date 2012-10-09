package coreservlets.web.bean;

import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Player;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "viewTableBean")
@Scope(value = "request")
public class ViewTableBean {

    private static final Logger LOGGER = Logger.getLogger(AddBattleBean.class);
    @Resource(name = "playerDAO")
    private PlayerDAO playerDAO;
    private List<Player> players = new ArrayList<Player>();

    @PostConstruct
    public void initPostDeclarative() {

        LOGGER.debug("######## LIST[Player] #######");
        List<Player> playersDao = playerDAO.findAll();
        for (Player Player : playersDao) {
            this.players.add(Player);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
