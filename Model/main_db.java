package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class main_db {
    public static void main(String[] args) throws MultipleSamePokemonException, UnknownPokemonException, TeamNameAlreadyExistsException, UnknownTeamException {

        //Test_ajout
        List <Team> all_teams3 = new ArrayList<>();
        List <Team> all_teams6 = new ArrayList<>();
        List<String> wanted_pokemons = new ArrayList<>();
        wanted_pokemons.add("Florizarre");
        wanted_pokemons.add("Dardargnan");
        wanted_pokemons.add("Papilusion");
        newTeam nt = new newTeam();
        Team team;
        team = nt.create(wanted_pokemons,"moha_crew");
        all_teams6.add(team);
        System.out.println("Équipe " + all_teams6.get(0).getName()+ " terminée, voici les pokémons :");
        for(Team t : all_teams6){
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
      /*  newTeam nt = new newTeam();
        nt.remove_team("nathan_crew",3);*/
    }
}

