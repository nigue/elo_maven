package coreservlets.buisness.dao;

import coreservlets.business.dao.EnfrentamientoDAO;
import coreservlets.business.model.Enfrentamiento;
import java.util.Date;
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
public class EnfrentamientoDAOTest {

    private static final Logger LOGGER = Logger.getLogger(EnfrentamientoDAOTest.class);
    @Resource(name = "enfrentamientoDAO")
    private EnfrentamientoDAO enfrentamientoDAO;

    @Test
    public void EnfrentamientoTestDAO() {
        LOGGER.debug("///////////////nicio de TEST");
        Enfrentamiento enfrentamiento = new Enfrentamiento();
        enfrentamiento.setPlayer1("kyo");
        enfrentamiento.setPlayer2("iori");
        enfrentamiento.setVictoriaPlayer1(true);
        enfrentamiento.setFecha(new Date());
        enfrentamientoDAO.persist(enfrentamiento);
        List<Enfrentamiento> enfrentamientos = enfrentamientoDAO.findAll();
        
        LOGGER.debug("/////////////// dato1: " + enfrentamiento.toString());
        LOGGER.debug("/////////////// dato2: " + enfrentamientos.get(0));
        
        Assert.assertFalse(enfrentamientos.isEmpty());
        Assert.assertEquals(enfrentamientos.size(), 1);
//        Assert.assertEquals(enfrentamiento, enfrentamientos.get(0));
    }
}
