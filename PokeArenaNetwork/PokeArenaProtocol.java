package PokeArenaNetwork;

import static PokeArenaNetwork.PokeArenaUtilities.createPacket;

public class PokeArenaProtocol {

   PokeArenaServer server;

   public PokeArenaProtocol(PokeArenaServer server) {
      this.server = server;
   }

   /**
    * Traite un paquet et retourne la réponse associé.
    * La réponse peut être nulle si le paquet entré en paramètre ne nécessite pas de réponse.
    *
    * @param request Paquet à traiter.
    * @return Paquet de réponse.
    */
   public PokeArenaPacket processPacket(PokeArenaPacket request) {
      PokeArenaPacket response;
      switch (request.getType()) {
         case PING:
            response = createPacket(PokeArenaPacketType.PONG, null);
            break;
         case MOVE:
            response = createPacket(PokeArenaPacketType.PONG, null);
            break;
         //TODO: Implémenter les autres cas
         default:
            response = null;
      }
      return response;
   }

}
