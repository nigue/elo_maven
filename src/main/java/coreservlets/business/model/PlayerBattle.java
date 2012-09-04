package coreservlets.business.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "player_battle")
@AssociationOverrides({
		@AssociationOverride(name = "pk.battle", 
			joinColumns = @JoinColumn(name = "BATTLE_ID")),
		@AssociationOverride(name = "pk.player", 
			joinColumns = @JoinColumn(name = "PLAYER_ID")) })
public class PlayerBattle extends PersistentIdEntity{
    
    private boolean victory;
    
    
}
