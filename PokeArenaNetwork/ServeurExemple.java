package PokeArenaNetwork;

public class ServeurExemple {

   public static void main(String[] args) throws InterruptedException {
      PokeArenaServer server = new PokeArenaServer("localhost", 8888);
      server.start();
      Thread.sleep(10000);
      server.broadcast("Ceci est un broadcast");
   }
}
