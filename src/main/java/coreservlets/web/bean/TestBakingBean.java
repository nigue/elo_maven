package coreservlets.web.bean;

import coreservlets.business.dao.PlayerDAO;
import coreservlets.business.model.Player;
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

@Service(value = "testBakingBean")
@Scope(value = "request")
public class TestBakingBean {

    private static final Logger LOGGER = Logger.getLogger(AddBattleBean.class);
    private String dato;
    private Player dato2;
    private Map<String, Player> lista = new LinkedHashMap<String,Player>();
    @Resource(name = "playerDAO")
    private PlayerDAO playerDAO;
    
    @PostConstruct
    public void initPostDeclarative(){
        this.dato = "hola";
        
        LOGGER.debug("######## inicio de lista #######");
        LOGGER.debug("######## MAP[Vista|DATA] #######");
        List<Player> playersDao = playerDAO.findAll();
        for (Player gamer : playersDao) {
            lista.put(gamer.getName(), gamer);
        }
    }
    
    public String doSubmit(){
        
        LOGGER.debug("######## submit #######");
        
        FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    getDato2() + " is data",
                    "INFO"));
            return null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Player getDato2() {
        return dato2;
    }

    public void setDato2(Player dato2) {
        this.dato2 = dato2;
    }

    public Map<String, Player> getLista() {
        return lista;
    }

    public void setLista(Map<String, Player> lista) {
        this.lista = lista;
    }
}
