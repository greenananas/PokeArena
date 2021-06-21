package PokeArena.PokeArenaNetwork.Packets;

import PokeArena.PokeArenaNetwork.Packets.PokeArenaPacket;
import PokeArena.PokeArenaNetwork.Packets.PokeArenaPacketType;

public class PokeArenaRefreshPacket extends PokeArenaPacket {

   public PokeArenaRefreshPacket() {
      super(PokeArenaPacketType.REFRESH);
   }

}
