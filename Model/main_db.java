package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class main_db {
    public static void main(String[] args) throws MultipleSamePokemonException, UnknownPokemonException, TeamNameAlreadyExistsException, UnknownTeamException {

        //Test_ajout
        List<String> wanted_pokemons = new ArrayList<>();
        newTeam.load_teams();
        wanted_pokemons.add("Tortank");
        wanted_pokemons.add("Dracaufeu");
        wanted_pokemons.add("Florizarre");
        newTeam nt = new newTeam();
        nt.create(wanted_pokemons,"last4_crew");
        System.out.println("Équipe " + newTeam.allTeams3.get(0).getName()+ " terminée, voici les pokémons :");
        for(Team t : newTeam.allTeams3){
            System.out.println("Équipe " + t.getName()+ " existante, voici les pokémons :");
            for(Pokemon pok : t.getPokemons()){
                System.out.println("Nom : " + pok.getName());
                System.out.println("Stats :");
                System.out.println(" HP  : " + pok.getHP() +
                        " Attaque : " + pok.getAttack() +
                        " Défense : " + pok.getDefence() +
                        " Attaque Spéciale : " + pok.getSpeAttack() +
                        " Défense Spéciale : " + pok.getSpeDefence() +
                        " Vitesse : " + pok.getSpeed());
                System.out.println("Moves :");
                System.out.println(" Attaque 1 : " + pok.getMove1().getName() +
                        " Attaque 2 : " + pok.getMove2().getName() +
                        " Attaque 3 : " + pok.getMove3().getName() +
                        " Attaque 4 : " + pok.getMove4().getName());
                System.out.println("Sprite de face :" + pok.getFont_sprite());
                System.out.println("Sprite de dos :" + pok.getBack_sprite());
                System.out.println("----------------------------------------------");
            }
        }

        //Test Suppression
        newTeam.remove_team("last3_crew",3);
    }
}

