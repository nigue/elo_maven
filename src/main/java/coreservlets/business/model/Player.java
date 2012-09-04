package coreservlets.business.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player extends PersistentIdEntity {

    private String name;
    private Set<PlayerBattle> playerBattle = new HashSet<PlayerBattle>(0);

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Set<PlayerBattle> playerBattle) {
        this.name = name;
        this.playerBattle = playerBattle;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.battle", cascade=CascadeType.ALL)
    public Set<PlayerBattle> getPlayerBattle() {
        return playerBattle;
    }

    public void setPlayerBattle(Set<PlayerBattle> playerBattle) {
        this.playerBattle = playerBattle;
    }
}
