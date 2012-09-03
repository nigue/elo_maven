package coreservlets.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.io.IOUtils;

@ManagedBean
@RequestScoped
public class RankingBean {

    private String resultados = null;
    public static final String userHome = System.getProperty("user.home");
    public static final String pathResultados = userHome + File.separator + "resultados.txt";
        
    public RankingBean() throws Exception {
        InputStream inputStream = new FileInputStream(pathResultados);
        this.resultados = IOUtils.toString(inputStream);        
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }
    
}
