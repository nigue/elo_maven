package coreservlets.web.pages;

import coreservlets.business.dao.EnfrentamientoDAO;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

public class IngresoBean {

    private static final Logger LOGGER = Logger.getLogger(IngresoBean.class);
    @Resource(name = "enfrentamientoDAO")
    private EnfrentamientoDAO enfrentamientoDAO;
    
    private String ganador;
    private String perdedor;

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(String perdedor) {
        this.perdedor = perdedor;
    }
}
