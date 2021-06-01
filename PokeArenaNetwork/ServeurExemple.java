package PokeArenaNetwork;

public class ServeurExemple {

    public static void main(String[] args) {
        PokeArenaServer server = new PokeArenaServer("localhost", 8888);
        server.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.broadcast("Ceci est un broadcast");
    }
}
