package coreservlets.business.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "battle")
public class Battle extends PersistentIdEntity {
    
    @OneToOne
    private Player winner;
    @OneToOne
    private Player losser;

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
    
}
