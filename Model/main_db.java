package Model;
import java.sql.*;

public class main_db {
    public static void main(String[] args) {
        newTeam nt = new newTeam();
        Team New_team = nt.create();
        System.out.println("Équipe terminée, voici les pokémons :");
        for(Pokemon pok : New_team.getPokemons()){
            System.out.println("Nom : " + pok.getName());
            System.out.println("Stats :");
            System.out.println(" HP  : " + pok.getHP() +
                    " Attaque : " + pok.getAttack() +
                    " Défense : " + pok.getDefense() +
                    " Attaque Spéciale : " + pok.getSpeAttack() +
                    " Défense Spéciale : " + pok.getSpeDefense() +
                    " Vitesse : " + pok.getSpeed());
            System.out.println("Moves :");
            System.out.println(" Attaque 1 : " + pok.getMove1().getName() +
                            " Attaque 2 : " + pok.getMove2().getName() +
                            " Attaque 3 : " + pok.getMove3().getName() +
                            " Attaque 4 : " + pok.getMove4().getName());
            System.out.println("----------------------------------------------");
        }
    }
}