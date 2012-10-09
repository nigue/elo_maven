package coreservlets.web.utils.converter;

import coreservlets.business.model.Player;
import coreservlets.web.bean.AddBackingBean;
import coreservlets.web.utils.data.DataUtils;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

@FacesConverter("player")
public class PlayerConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(AddBackingBean.class);
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOGGER.debug("########### Entrando a convertidor ##########");
//        LOGGER.debug(value);
//        LOGGER.debug("########### 11 ##########");
        DataUtils dataUtils = new DataUtils();
//        LOGGER.debug("########### 22 ##########");
        List<Player> playerByName = dataUtils.getPlayerByName(value);
//        LOGGER.debug("########### 33 ##########");
//        for (Player p : playerByName) {
//            LOGGER.debug("--------list---------->" + p.toString());
//        }
//        LOGGER.debug("--------array---------->" + playerByName.get(0));
//        LOGGER.debug("########### 44 ##########");
        return playerByName.get(0);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
