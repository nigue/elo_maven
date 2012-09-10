package coreservlets.business.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "enfrentamiento")
public class Enfrentamiento extends GeneratedIdEntity {

    @Column(name = "player1", nullable=false)
    private String player1;
    @Column(name = "player2", nullable=false)
    private String player2;
    @Column(name = "victoria_player1", nullable=false)
    private boolean victoriaPlayer1;
    @Column(name = "fecha", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public boolean isVictoriaPlayer1() {
        return victoriaPlayer1;
    }

    public void setVictoriaPlayer1(boolean victoriaPlayer1) {
        this.victoriaPlayer1 = victoriaPlayer1;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
