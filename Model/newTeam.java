package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class newTeam {

    public static void main(String[] args) {
        System.out.println("Salut, veux-tu créer une équipé de 3 ou 6 pokémons ?");
        System.out.println("1 - Equipe de 3");
        System.out.println("2 - Equipe de 6");
        System.out.println("3 - Quitter");

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        //Nombre de pokémons restants à ajouter dans l'équipe
        int nb_pok = 0;

        switch (choix) {
            case 1 -> {
                System.out.println("Team de 3");
                nb_pok = 3;
            }
            case 2 -> {
                nb_pok = 6;
                System.out.println("team de 6");
            }
            default -> {
                System.out.println("Aurevoir");
                System.exit(1);
            }
        }


        System.out.println("Voici la liste des Pokémon existants :");
        Connection con = dbConnection.connect();

        try{
            String sql = "SELECT * FROM pokemon";
            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(sql);
            System.out.println("Liste des Pokémons :");
            while(result.next()){
                String name = result.getString("pretty_name");
                System.out.println("Nom : " + name);
            }
        }
        catch (SQLException e){
            System.out.println("mais quoi");
        }



        while (nb_pok > 0){
            System.out.println("Quel est le nom du Pokémon à ajouter ?");

        }
    }
}
