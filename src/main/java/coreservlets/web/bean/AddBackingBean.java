package coreservlets.web.bean;

import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Player;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "addBackingBean")
@Scope(value="session")
public class AddBackingBean {

    private static final Logger LOGGER = Logger.getLogger(AddBackingBean.class);
    @Resource(name = "playerDAO")
    private PlayerDAO playerDAO;
    private String player;

    public void validator(
            FacesContext context,
            UIComponent component,
            Object value) throws ValidatorException {
        LOGGER.debug("######## VALIDATION ########");

        String playerData = (String) value;
        List<Player> players = playerDAO.findByField("name", playerData);
        if (!players.isEmpty()) {
            LOGGER.debug(playerData + " is already on DB");
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_WARN,
                    playerData + " is already on DB",
                    "WARNING"));
        }
    }

    public String doSubmit() {
        try {
            Player playerNew = new Player();
            List<Player> players = playerDAO.findAll();
            Integer id = new Integer(players.size() + 1);
            playerNew.setId(id.longValue());
            playerNew.setName(getPlayer());
            playerNew.setElo(1500);
            playerNew.setNumGames(0);
            playerDAO.persist(playerNew);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    getPlayer() + " is new on DB",
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

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
