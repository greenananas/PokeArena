package PokeArenaNetwork;

public class PokeArenaProtocol {

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
            response = new PokeArenaPongPacket();
            break;
         //TODO: Implémenter les autres cas
         default:
            response = null;
      }
      return response;
   }

}
