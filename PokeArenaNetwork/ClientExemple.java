package PokeArenaNetwork;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ClientExemple {

   public static void main(String[] args) throws URISyntaxException {
      PokeArenaClient client = new PokeArenaClient(new URI("ws://localhost:8888"));
      client.connect();

      Scanner sc = new Scanner(System.in);
      String input = sc.next();


      while (!input.equals("quit")) {
         if (input.equals("ping")) client.sendPing();
         input = sc.next();
      }
      client.close();
   }
}
