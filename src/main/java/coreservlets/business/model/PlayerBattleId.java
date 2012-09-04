package coreservlets.business.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PlayerBattleId implements Serializable {

    private Player player;
    private Battle battle;

    @ManyToOne
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne
    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerBattleId that = (PlayerBattleId) o;

        if (getPlayer() != null ? !getPlayer().equals(that.player) : that.player != null) {
            return false;
        }
        if (getBattle() != null ? !getBattle().equals(that.battle) : that.battle != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.player != null ? this.player.hashCode() : 0);
        hash = 23 * hash + (this.battle != null ? this.battle.hashCode() : 0);
        return hash;
    }
}
