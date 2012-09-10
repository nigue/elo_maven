package coreservlets.business.model;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "player_battle")
@AssociationOverrides({
    @AssociationOverride(name = "pk.battle",
    joinColumns =
    @JoinColumn(name = "BATTLE_ID")),
    @AssociationOverride(name = "pk.player",
    joinColumns =
    @JoinColumn(name = "PLAYER_ID"))})
public class PlayerBattle implements Serializable {

    private PlayerBattleId pk = new PlayerBattleId();
    private boolean victory;

    public PlayerBattle() {
    }

    @EmbeddedId
    public PlayerBattleId getPk() {
        return pk;
    }

    public void setPk(PlayerBattleId pk) {
        this.pk = pk;
    }

    @Transient
    public Player getPlayer() {
        return getPk().getPlayer();
    }

    public void setPlayer(Player player) {
        getPk().setPlayer(player);
    }

    @Transient
    public Battle getBattle() {
        return getPk().getBattle();
    }

    public void setBattle(Battle battle) {
        getPk().setBattle(battle);
    }

    @Column(name = "victory", nullable = false)
    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerBattle that = (PlayerBattle) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
