package PokeArenaNetwork.Packets;

import PokeArenaNetwork.Packets.PokeArenaPacket;
import PokeArenaNetwork.Packets.PokeArenaPacketType;

public class PokeArenaRefreshPacket extends PokeArenaPacket {

   public PokeArenaRefreshPacket() {
      super(PokeArenaPacketType.REFRESH);
   }

}
