package coreservlets.web.bean;

import coreservlets.business.dao.BattleDAO;
import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Battle;
import coreservlets.business.model.Player;
import coreservlets.web.utils.data.DataUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "addBattleBean")
@Scope(value = "prototype")
public class AddBattleBean {

    private static final Logger LOGGER = Logger.getLogger(AddBattleBean.class);
    @Resource(name = "playerDAO")
    private PlayerDAO playerDAO;
    @Resource(name = "battleDAO")
    private BattleDAO battleDAO;
    private Map<String, Player> players = new LinkedHashMap<String, Player>();
    private Player winner;
    private Player losser;

    @PostConstruct
    public void initPostDeclarative() {

        LOGGER.debug("######## MAP[Vista|DATA] #######");
        List<Player> playersDao = playerDAO.findAll();
        for (Player gamer : playersDao) {
            players.put(gamer.getName(), gamer);
        }
    }

    public String doSubmit() {
        LOGGER.debug("######## inicio de agregar batalla #######");
        LOGGER.debug("######## winner " + getWinner().getName() + " #######");
        LOGGER.debug("######## losser " + getLosser().getName() + " #######");
        if (getWinner().getName().equals(getLosser().getName())) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "winner is iqual to losser",
                    "ERROR"));
            return null;
        }
        try {
            Battle battleNew = new Battle();
            List<Battle> battles = getBattleDAO().findAll();
            Integer id = new Integer(battles.size() + 1);
            battleNew.setId(id.longValue());
            battleNew.setWinner(winner);
            battleNew.setLosser(losser);
            DataUtils dataUtils = new DataUtils();
            dataUtils.doElo(winner, losser);
            getBattleDAO().persist(battleNew);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "battle" + id.longValue() + " is new on DB",
                    "INFO"));
            return null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    e.getMessage(),
                    "ERROR"));
            return null;
        }
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getLosser() {
        return losser;
    }

    public void setLosser(Player losser) {
        this.losser = losser;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    public PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public BattleDAO getBattleDAO() {
        return battleDAO;
    }

    public void setBattleDAO(BattleDAO battleDAO) {
        this.battleDAO = battleDAO;
    }
}
