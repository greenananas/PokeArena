package Model;
import java.util.ArrayList;
import java.util.List;

public class main_db {
    public static void main(String[] args) throws MultipleSamePokemonException, UnknownPokemonException, TeamNameAlreadyExistsException, UnknownTeamException, WrongTypeBDDException {

        //Test_ajout
        /*ist<String> wanted_pokemons = new ArrayList<>(); */
        TeamBuilder.load_teams();
        /*wanted_pokemons.add("Dracaufeu");
        wanted_pokemons.add("Rattatac");
        wanted_pokemons.add("Roucarnage");
        wanted_pokemons.add("Dardargnan");
        wanted_pokemons.add("Simularbre");
        wanted_pokemons.add("Arbok");
        TeamBuilder nt = new TeamBuilder();
        nt.create(wanted_pokemons,"team6-2");
        System.out.println("Équipe " + TeamBuilder.allTeams6.get(TeamBuilder.allTeams6.size() - 1).getName()+ " terminée, voici les pokémons :");
        System.out.println("Affichage des Teams3");
        for(Team t : TeamBuilder.allTeams6){
            System.out.println("----------------------------------------------------------");
            for(Pokemon pok : t.getPokemons()){
                System.out.println("Identifiant du Pokémon : " + pok.getId());
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
                System.out.println("----------------------------------------------");
            }
        }*/
        //Test Suppression
        /*TeamBuilder.remove_team("last3_crew",3);*/
        String findTeam = "team6-1";
        Team t = TeamBuilder.getTeamByName(findTeam, TeamBuilder.allTeams6);
        System.out.println("Team : " + t.getName() + " trouvé !");
    }
}

