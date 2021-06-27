package pokearena.network.packets;

import pokearena.battle.Action;

/**
 * Modélise le format des paquets utilisés envoyer des actions.
 *
 * @author Louis
 */
public class PokeArenaActionPacket extends PokeArenaPacket {

    /**
     * Action contenu par le paquet.
     */
    private Action action = null;

    /**
     * Créer un paquet action.
     */
    public PokeArenaActionPacket() {
        super(PokeArenaPacketType.ACTION);
    }

    /**
     * Créer un paquet action qui contient l'action fourni en paramètres.
     *
     * @param action Action qui va être contenu par le paquet.
     */
    public PokeArenaActionPacket(Action action) {
        this();
        this.action = action;
    }

    /**
     * Obtenir l'action contenu dans le paquet.
     * @return
     */
    public Action getAction() {
        return action;
    }

}
