package coreservlets.business.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "battle")
public class Battle extends PersistentIdEntity {

    private Set<PlayerBattle> playerBattle = new HashSet<PlayerBattle>(0);

    public Battle() {
    }

    public Battle(Set<PlayerBattle> playerBattle) {
        this.playerBattle = playerBattle;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.battle")
    public Set<PlayerBattle> getPlayerBattle() {
        return playerBattle;
    }

    public void setPlayerBattle(Set<PlayerBattle> playerBattle) {
        this.playerBattle = playerBattle;
    }
    
}
