package coreservlets.web;

import java.io.FileWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.commons.io.IOUtils;

@ManagedBean
@RequestScoped
public class CalculoBean {

    @ManagedProperty(value="#{rankingBean}")
    private RankingBean rankingBean;
    
    private List<Jugador> jugadores = null;
    
    public String calcular() throws Exception {
        Writer resultadosWriter = new FileWriter(RankingBean.pathResultados);
        IOUtils.copy(new StringReader(rankingBean.getResultados()), resultadosWriter);
        IOUtils.closeQuietly(resultadosWriter);
        this.setJugadores(calcular(rankingBean.getResultados()));
        return "resultados";
    }

    public RankingBean getRankingBean() {
        return rankingBean;
    }

    public void setRankingBean(RankingBean rankingBean) {
        this.rankingBean = rankingBean;
    }
    
    public static List<Jugador> calcular(String valor) throws Exception {
        List<String> lineas = IOUtils.readLines(new StringReader(valor));
        
        List<Jugador> jugadores = new ArrayList<Jugador>();
        
        for(String linea: lineas) {
            if (!linea.isEmpty()) {
                String[] partes = linea.split(";");
                String nomJugador1 = partes[0];
                String nomJugador2 = partes[1];
                Jugador jugador1 = new Jugador(nomJugador1);
                Jugador jugador2 = new Jugador(nomJugador2);
                int pos1 = jugadores.indexOf(jugador1);
                int pos2 = jugadores.indexOf(jugador2);
                if (pos1 >= 0) {
                    jugador1 = jugadores.get(pos1);
                } else {
                    jugadores.add(jugador1);
                }
                if (pos2 >= 0) {
                    jugador2 = jugadores.get(pos2);
                } else {
                    jugadores.add(jugador2);
                }
                int resultado = Integer.parseInt(partes[2]);
                calculoElo(jugador1, jugador2, resultado);                
            }
        }
        
        // Ordenamos
        Collections.sort(jugadores);
        return jugadores;
        
    }
    
    public static double getK(Jugador jugador) {        
        if (jugador.getElo() > 2000 || jugador.getNumPartidas() > 40) {
            return 10;
        }
        if (jugador.getNumPartidas() > 20) {
            return 15;
        }
        return 25;
    }
    
    public static int getELO(Jugador jugador1, Jugador jugador2, int resultado) {
        double expected = 1 / ( 1 + Math.pow( 10, ( ( jugador2.getElo() - jugador1.getElo() ) / 400 ) ) );
        double K = getK(jugador1);
        double newElo = jugador1.getElo() + Math.round( K * ( ( 1 - 0.25 ) + 0.25 * ( ( 10 - 0 ) / 10 ) ) * ( resultado - expected ) );
        return Math.round((float)newElo);
    }
    
    public static void calculoElo(Jugador jugador1, Jugador jugador2, int resultado) {
        
        int elo1 = getELO(jugador1, jugador2, resultado);
        int elo2 = getELO(jugador2, jugador1, Math.abs(1- resultado));
        
        jugador1.setElo(elo1);
        jugador1.setNumPartidas(jugador1.getNumPartidas() + 1);

        jugador2.setElo(elo2);
        jugador2.setNumPartidas(jugador2.getNumPartidas() + 1);
        
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
}
